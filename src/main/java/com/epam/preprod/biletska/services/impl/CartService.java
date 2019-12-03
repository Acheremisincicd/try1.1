package com.epam.preprod.biletska.services.impl;

import com.epam.preprod.biletska.dto.CartDto;
import com.epam.preprod.biletska.entity.Item;
import com.epam.preprod.biletska.entity.Order;
import com.epam.preprod.biletska.entity.OrderStatus;
import com.epam.preprod.biletska.entity.Product;
import com.epam.preprod.biletska.entity.User;
import com.epam.preprod.biletska.services.ICartService;
import com.epam.preprod.biletska.services.IOrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.epam.preprod.biletska.servlets.CommonDefinitions.CURRENT_CART;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.LOGGED_USER;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.getSessionAttr;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.setSessionAttr;

public class CartService implements ICartService {

    private IOrderService orderService;

    public CartService(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void addProductToCart(CartDto cart, Product product, int amount) {
        List<Item> listOfItems = cart.getProductItems();
        for(Item item : listOfItems){
            if(item.getProduct().getProductId() == product.getProductId()){
                int quantity = item.getQuantity() + amount;
                item.setQuantity(quantity);
                item.setTotal(product.getPrice().floatValue() * quantity);
                recalculateOrderTotal(cart);
                orderService.update(cart.getOrder());
                return;
            }
        }
        listOfItems.add(orderService.addItemToOrder(new Item(
                cart.getOrder(),
                product,
                product.getPrice().floatValue(),
                amount,
                product.getPrice().floatValue() * amount)));
        recalculateOrderTotal(cart);
        orderService.update(cart.getOrder());
    }

    @Override
    public void recalculateOrderTotal(CartDto cart) {
        float itemsTotal = cart.getProductItems().stream()
                .map(Item::getTotal).collect(Collectors.summingDouble(i -> i))
                .floatValue();
        cart.getOrder().setTotal(itemsTotal);
    }

    @Override
    public void removeProductFromCart(CartDto cart, Product product) {
        cart.getProductItems().stream()
                .filter(i -> i.getProduct().equals(product))
                .findFirst()
                .ifPresent(i -> {
                            orderService.removeItemFromOrder(i);
                            cart.getProductItems().remove(i);
                        }
                );
    }

    @Override
    public Optional<Product> isProductInCart(int productId, CartDto cart) {
        return cart.getProductItems().stream()
                .map(Item::getProduct)
                .filter(e -> e.getKey() == productId).findFirst();
    }

    @Override
    public Optional<Item> getItemOfProductInCart(int productId, CartDto cart) {
        return cart.getProductItems().stream()
                .filter(i -> i.getProduct().getProductId() == productId)
                .findFirst();
    }

    @Override
    public boolean isCartExists(HttpServletRequest request) {
        return getSessionAttr(request, CartDto.class, CURRENT_CART).isPresent();
    }

    @Override
    public CartDto getOrCreateCart(HttpServletRequest request) {
        CartDto cartDto = getSessionAttr(request, CartDto.class, CURRENT_CART).orElseGet(() ->
                new CartDto(
                        orderService.create(
                                new Order(
                                        new Date(),
                                        getSessionAttr(request, User.class, LOGGED_USER).get().getEmail(),
                                        new Date(),
                                        0f,
                                        OrderStatus.CREATED,
                                        "New order has been created by: " + getSessionAttr(request, User.class, LOGGED_USER).get().getEmail())),
                        new ArrayList<>(),
                        getSessionAttr(request, User.class, LOGGED_USER).get()));
        setSessionAttr(request, CURRENT_CART, cartDto);
        return cartDto;
    }
}
