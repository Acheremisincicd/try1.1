package com.epam.preprod.biletska.listener;

import com.epam.preprod.biletska.Constants;
import com.epam.preprod.biletska.captcha.CaptchaContainer;
import com.epam.preprod.biletska.captcha.CaptchaFactory;
import com.epam.preprod.biletska.captcha.CaptchaScheduler;
import com.epam.preprod.biletska.dao.IUserDao;
import com.epam.preprod.biletska.dao.impl.CategoryDao;
import com.epam.preprod.biletska.dao.impl.ItemDao;
import com.epam.preprod.biletska.dao.impl.ManufacturerDao;
import com.epam.preprod.biletska.dao.impl.OrderDao;
import com.epam.preprod.biletska.dao.impl.ProductDao;
import com.epam.preprod.biletska.dao.impl.UserDao;
import com.epam.preprod.biletska.locale.LocaleStrategy;
import com.epam.preprod.biletska.locale.impl.CookieLocaleStrategy;
import com.epam.preprod.biletska.locale.impl.SessionLocaleStrategy;
import com.epam.preprod.biletska.services.ICaptchaService;
import com.epam.preprod.biletska.services.ICartService;
import com.epam.preprod.biletska.services.IImageService;
import com.epam.preprod.biletska.services.IOrderService;
import com.epam.preprod.biletska.services.IProductService;
import com.epam.preprod.biletska.services.IUserService;
import com.epam.preprod.biletska.services.impl.CartService;
import com.epam.preprod.biletska.services.impl.ImageService;
import com.epam.preprod.biletska.services.impl.OrderService;
import com.epam.preprod.biletska.services.impl.ProductService;
import com.epam.preprod.biletska.services.impl.UserService;
import com.epam.preprod.biletska.transaction.TransactionDispatcher;
import com.epam.preprod.biletska.util.XmlUtils;
import com.epam.preprod.biletska.validation.IValidationService;
import com.epam.preprod.biletska.validation.impl.ValidationService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static com.epam.preprod.biletska.Constants.USER_SERVICE;

/**
 * The type Context captcha listener.
 */
@WebListener
public class ContextListener implements ServletContextListener {

    private ScheduledExecutorService schedulerService = Executors.newScheduledThreadPool(1);
    private Map<String, Supplier<LocaleStrategy>> localeStrategies = new HashMap<>();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        /**
         * Initialize captcha related services
         */
        String captchaServiceName = servletContext.getInitParameter(Constants.CAPTCHA_SERVICE);
        int captchaLifeSpan = Integer.parseInt(servletContext.getInitParameter(Constants.CAPTCHA_LIFE_SPAN));
        CaptchaInit(servletContext, captchaServiceName, captchaLifeSpan);

        /**
         * DAO initialize
         */
        IUserDao userDao = new UserDao();
        servletContext.setAttribute(Constants.USER_DAO, userDao);

        /**
         *  Initialize user services
         */
        TransactionDispatcher transactionDispatcher = new TransactionDispatcher();
        IUserService userService = new UserService(new UserDao(), transactionDispatcher);
        servletContext.setAttribute(USER_SERVICE, userService);

        /**
         *  Initialize image service
         */
        IImageService imageService = new ImageService();
        servletContext.setAttribute(Constants.IMAGE_SERVICE, imageService);

        /**
         *  Initialize validation service
         */
        IValidationService validationService = new ValidationService();
        servletContext.setAttribute(Constants.VALIDATION_SERVICE, validationService);

        /**
         *  Initialize product service
         */
        ProductDao productDao = new ProductDao(new CategoryDao(), new ManufacturerDao());
        IProductService productService = new ProductService(productDao, transactionDispatcher);
        servletContext.setAttribute(Constants.PRODUCT_SERVICE, productService);

        /**
         *  Initialize order service
         */
        OrderDao orderDao = new OrderDao();
        IOrderService orderService = new OrderService(orderDao, new ItemDao(productDao, orderDao), transactionDispatcher);
        servletContext.setAttribute(Constants.ORDER_SERVICE, orderService);

        /**
         *  Initialize cart service
         */
        ICartService cartService = new CartService(orderService);
        servletContext.setAttribute(Constants.CART_SERVICE, cartService);

        /**
         * Locale initialize
         */
        initLocaleStrategyMap();
        initI18n(servletContext);

        /**
         * Security initialize
         */
        String pathToXML = servletContextEvent.getServletContext().getInitParameter("path-file");
        servletContextEvent.getServletContext().setAttribute("constraints", XmlUtils.parseXML(pathToXML));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        schedulerService.shutdown();
    }

    private void CaptchaInit(ServletContext servletContext, String captchaServiceName, int captchaLifeSpan) {
        CaptchaContainer captchaContainer = new CaptchaContainer();
        CaptchaFactory captchaFactory = new CaptchaFactory(captchaContainer, captchaLifeSpan);
        ICaptchaService captchaService = captchaFactory.getService(captchaServiceName);

        servletContext.setAttribute(Constants.CAPTCHA_SERVICE, captchaService);
        servletContext.setAttribute(Constants.CAPTCHA_LIFE_SPAN, captchaLifeSpan);

        setCaptchaScheduler(captchaLifeSpan, captchaContainer);
    }

    private void setCaptchaScheduler(int captchaLifeSpan, CaptchaContainer captchaContainer) {
        CaptchaScheduler scheduler = new CaptchaScheduler(captchaContainer);
        schedulerService.schedule(scheduler, captchaLifeSpan, TimeUnit.MILLISECONDS);
    }

    private void initLocaleStrategyMap() {
        localeStrategies.put("Cookie", CookieLocaleStrategy::new);
        localeStrategies.put("Session", SessionLocaleStrategy::new);
    }

    private void initI18n(ServletContext servletContext) {
        String[] supportedLocales = servletContext.getInitParameter("locales").split(",");

        servletContext.setAttribute("locales", Arrays.asList(supportedLocales));
        servletContext.setAttribute("defaultLocale", servletContext.getInitParameter("defaultLocale"));

        String localeStrategyName = servletContext.getInitParameter("localeStrategy");
        LocaleStrategy localeStrategyImpl = localeStrategies.get(localeStrategyName).get();
        servletContext.setAttribute("localeStrategy", localeStrategyImpl);

        int localeCookieMaxAge = Integer.parseInt(servletContext.getInitParameter("localeCookieMaxAge"));
        servletContext.setAttribute("localeCookieMaxAge", localeCookieMaxAge);
    }
}
