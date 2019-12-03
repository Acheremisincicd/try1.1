package com.epam.preprod.biletska.servlets;

import com.epam.preprod.biletska.Constants;
import com.epam.preprod.biletska.dto.CartDto;
import com.epam.preprod.biletska.entity.Item;
import com.epam.preprod.biletska.entity.Product;
import com.epam.preprod.biletska.entity.User;
import com.epam.preprod.biletska.services.ICartService;
import com.epam.preprod.biletska.services.IOrderService;
import com.epam.preprod.biletska.services.IProductService;
import com.epam.preprod.biletska.services.impl.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.epam.preprod.biletska.servlets.CommonDefinitions.CURRENT_CART;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.LOGGED_USER;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.ROUTE_ROOT;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.getRequestParamInt;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.getRequestParamString;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.getSessionAttr;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.raiseExceptionIfNoParam;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.removeSessionAttr;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.setRequestAttr;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(CartServlet.class);
    private final String PARAM_PRODUCT_ID = "productId";
    private final String PARAM_PRODUCT_AMOUNT = "productAmount";
    private static final String ERROR_ITEM_PICKUP = "errorItemPickup";
    private IProductService productService;
    private IOrderService orderService;
    private ICartService cartService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.productService = raiseExceptionIfNoParam(config, Constants.PRODUCT_SERVICE, IProductService.class);
        this.orderService = raiseExceptionIfNoParam(config, Constants.ORDER_SERVICE, IOrderService.class);
        this.cartService = raiseExceptionIfNoParam(config, Constants.CART_SERVICE, CartService.class);
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!getSessionAttr(request, User.class, LOGGED_USER).isPresent()) {
            response.sendRedirect(ROUTE_ROOT);
            return;
        }
        String PARAM_ACTION = "action";
        Optional<String> actionOpt = getRequestParamString(request, PARAM_ACTION);
        if (actionOpt.isPresent()) {
            String VALUE_ACTION_REMOVE = "remove";
            String VALUE_ACTION_UPDATE = "update";
            String VALUE_ACTION_CANCEL = "cancel";
            String VALUE_ACTION_CHECKOUT = "checkout";
            if (actionOpt.get().equalsIgnoreCase(VALUE_ACTION_UPDATE)) {
                updateCart(request, response);
            } else if (actionOpt.get().equalsIgnoreCase(VALUE_ACTION_REMOVE)) {
                removeFromCart(request, response);
            } else if (actionOpt.get().equalsIgnoreCase(VALUE_ACTION_CANCEL)) {
                cancelCart(request, response);
            } else if (actionOpt.get().equalsIgnoreCase(VALUE_ACTION_CHECKOUT)) {
                checkoutCart(request, response);
            }
        } else {
            displayCart(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (!getSessionAttr(request, User.class, LOGGED_USER).isPresent()) {
            response.sendRedirect(ROUTE_ROOT);
            return;
        }
        CommonDefinitions.ErrorCollector errors = CommonDefinitions.ErrorCollector.getInstance();
        getRequestParamInt(request, PARAM_PRODUCT_ID).ifPresent(prodId ->
                getRequestParamInt(request, PARAM_PRODUCT_AMOUNT).ifPresent(amount -> {
                    if (amount > 0) {
                        String id = String.valueOf(prodId);
                        cartService.addProductToCart(cartService.getOrCreateCart(request), productService.get(id), amount);
                    } else {
                        errors.collect(ERROR_ITEM_PICKUP, "No products selected");
                    }
                })
        );
        errors.getErrors().ifPresent(e -> setRequestAttr(request, Constants.SHOPPING_CART_ERRORS, e));
        response.sendRedirect(CommonDefinitions.ROUTE_PRODUCTS);
    }

    private void displayCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getSessionAttr(request, CartDto.class, CURRENT_CART).ifPresent(c -> setRequestAttr(request, CURRENT_CART, c));
        request.getRequestDispatcher(Constants.CART_JSP).forward(request, response);
    }

    private void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        removeFromCart(request, response, false);
    }

    private void removeFromCart(HttpServletRequest request, HttpServletResponse response, boolean internalRemove) throws IOException {
        if (cartService.isCartExists(request)) {
            getRequestParamInt(request, PARAM_PRODUCT_ID).ifPresent(productId -> {
                CartDto cart = cartService.getOrCreateCart(request);
                cartService.isProductInCart(productId, cart).ifPresent(p -> cartService.removeProductFromCart(cart, p));
                cartService.recalculateOrderTotal(cart);
            });
        }
        if (!internalRemove) {
            response.sendRedirect(Constants.CART_JSP);
        }
    }

    private void checkoutCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CartDto cart = cartService.getOrCreateCart(request);
        orderService.checkoutOrder(cart.getOrder());
        cart.clearCart();
        removeSessionAttr(request, CURRENT_CART);
        response.sendRedirect(Constants.CHECKOUT_JSP);
    }

    private void cancelCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CartDto cart = cartService.getOrCreateCart(request);
        orderService.cancelOrder(cart.getOrder(), "Canceled by customer: " + cart.getUser().getEmail());
        cart.clearCart();
        removeSessionAttr(request, CURRENT_CART);
        response.sendRedirect(Constants.CART_JSP);
    }

    private void updateCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        getRequestParamInt(request, PARAM_PRODUCT_ID).ifPresent(productId ->
                getRequestParamInt(request, PARAM_PRODUCT_AMOUNT).ifPresent(amount -> {
                    if (amount < 1) {
                        try {
                            removeFromCart(request, response, true);
                        } catch (IOException e) {
                            LOGGER.error("Error occurs when update Cart: {}", e.getMessage());
                        }
                    } else {
                        CartDto cart = cartService.getOrCreateCart(request);
                        cartService.getItemOfProductInCart(productId, cart).ifPresent(i -> {
                                    if (amount != i.getQuantity()) {
                                        i.setQuantity(amount);
                                        float newTotal = amount * i.getProduct().getPrice().floatValue();
                                        i.setTotal(newTotal);
                                        cartService.recalculateOrderTotal(cart);
                                    }
                                }
                        );
                    }
                }));
        response.sendRedirect(Constants.CART_JSP);
    }
}