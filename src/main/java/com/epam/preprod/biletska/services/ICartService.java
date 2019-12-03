package com.epam.preprod.biletska.services;

import com.epam.preprod.biletska.dto.CartDto;
import com.epam.preprod.biletska.entity.Item;
import com.epam.preprod.biletska.entity.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Cart service interface
 */
public interface ICartService {

    /**
     * Add product to the cart.
     *
     * @param cart    cart
     * @param product product
     * @param amount  amount
     */
    void addProductToCart(CartDto cart, Product product, int amount);

    /**
     * Recalculate cost of the cart.
     *
     * @param cart cart
     */
    void recalculateOrderTotal(CartDto cart);

    /**
     * Remove product from the cart.
     *
     * @param cart    cart
     * @param product product
     */
    void removeProductFromCart(CartDto cart, Product product);

    /**
     * Check products existence in the cart.
     *
     * @param productId product if
     * @param cart      cart
     * @return product
     */
    Optional<Product> isProductInCart(int productId, CartDto cart);

    /**
     * Get item of product in the cart.
     *
     * @param productId product id
     * @param cart      cart
     * @return item
     */
    Optional<Item> getItemOfProductInCart(int productId, CartDto cart);

    /**
     * Check if cart exists.
     *
     * @param request request
     * @return boolean
     */
    boolean isCartExists(HttpServletRequest request);

    /**
     * Get or create cart.
     *
     * @param request request
     * @return cart dto
     */
    CartDto getOrCreateCart(HttpServletRequest request);
}
