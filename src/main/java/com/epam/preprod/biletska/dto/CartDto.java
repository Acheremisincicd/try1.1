package com.epam.preprod.biletska.dto;

import com.epam.preprod.biletska.entity.Item;
import com.epam.preprod.biletska.entity.Order;
import com.epam.preprod.biletska.entity.User;

import java.util.List;
import java.util.Objects;

/**
 * The type Cart DTO.
 */
public class CartDto {

    private Order order;
    private List<Item> productItems;
    private User user;

    /**
     * Instantiates a new Cart Dto.
     */
    public CartDto() {
    }

    public CartDto(Order order, List<Item> productItems, User user) {
        this.order = order;
        this.productItems = productItems;
        this.user = user;
    }

    /**
     * Gets order.
     *
     * @return the name
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets a new order for cart.
     *
     * @param order order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Gets cart's items.
     *
     * @return the cart items
     */
    public List<Item> getProductItems() {
        return productItems;
    }

    /**
     * Sets a new order for cart.
     *
     * @param productItems items in carr
     */
    public void setProductItems(List<Item> productItems) {
        this.productItems = productItems;
    }

    /**
     * Gets order.
     *
     * @return the name
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user current user
     */
    public void setUser(User user) {
        this.user = user;
    }

    public void clearCart() {
        getProductItems().clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CartDto other = (CartDto) o;
        return getOrder().equals(other.getOrder())
                && getProductItems().equals(other.getProductItems())
                && getUser().equals(other.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrder(), getProductItems().hashCode(), getUser());
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "order='" + getOrder() + '\'' +
                ", productItems=" + getProductItems() +
                ", user=" + getUser() +
                '}';
    }
}
