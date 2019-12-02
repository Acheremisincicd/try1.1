package com.epam.preprod.biletska.listener;

import com.epam.preprod.biletska.Constants;
import com.epam.preprod.biletska.captcha.CaptchaContainer;
import com.epam.preprod.biletska.captcha.CaptchaFactory;
import com.epam.preprod.biletska.captcha.CaptchaScheduler;
import com.epam.preprod.biletska.dao.IUserDao;
import com.epam.preprod.biletska.dao.impl.UserDao;
import com.epam.preprod.biletska.services.ICaptchaService;
import com.epam.preprod.biletska.services.IImageService;
import com.epam.preprod.biletska.services.IUserService;
import com.epam.preprod.biletska.services.impl.ImageService;
import com.epam.preprod.biletska.services.impl.UserServiceUserService;
import com.epam.preprod.biletska.transaction.TransactionDispatcher;
import com.epam.preprod.biletska.validation.IValidationService;
import com.epam.preprod.biletska.validation.impl.ValidationService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.epam.preprod.biletska.Constants.USER_SERVICE;

/**
 * The type Context captcha listener.
 */
@WebListener
public class ContextListener implements ServletContextListener {

    private ScheduledExecutorService schedulerService = Executors.newScheduledThreadPool(1);

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
        IUserService userService = new UserServiceUserService(new UserDao(), transactionDispatcher);
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
    }

    private void CaptchaInit(ServletContext servletContext, String captchaServiceName, int captchaLifeSpan) {
        CaptchaContainer captchaContainer = new CaptchaContainer();
        CaptchaFactory captchaFactory = new CaptchaFactory(captchaContainer, captchaLifeSpan);
        ICaptchaService captchaService = captchaFactory.getService(captchaServiceName);

        servletContext.setAttribute(Constants.CAPTCHA_SERVICE, captchaService);
        servletContext.setAttribute(Constants.CAPTCHA_LIFE_SPAN, captchaLifeSpan);

        setCaptchaScheduler(captchaLifeSpan, captchaContainer);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        schedulerService.shutdown();
    }

    private void setCaptchaScheduler(int captchaLifeSpan, CaptchaContainer captchaContainer) {
        CaptchaScheduler scheduler = new CaptchaScheduler(captchaContainer);
        schedulerService.schedule(scheduler, captchaLifeSpan, TimeUnit.MILLISECONDS);
    }
}
