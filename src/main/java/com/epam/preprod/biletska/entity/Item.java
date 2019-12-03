package com.epam.preprod.biletska.entity;

import java.util.Objects;

/**
 * Order item
 */
public class Item {

    private int itemId;
    private Order order;
    private Product product;
    private float actualPrice;
    private int quantity;
    private float total;

    /**
     * Instantiates a new order item.
     */
    public Item() {
    }

    /**
     * Instantiates a new order item.
     *
     * @param order       the order
     * @param product     the product
     * @param actualPrice the price of the priduct in order
     * @param quantity    the amount of items in order
     * @param total       the costs amount for item
     */
    public Item(Order order, Product product, float actualPrice, int quantity, float total) {
        this.order = order;
        this.product = product;
        this.actualPrice = actualPrice;
        this.quantity = quantity;
        this.total = total;
    }

    /**
     * Gets item id.
     *
     * @return the identifier
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Sets item id.
     *
     * @param itemId identifier
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * Gets Order for item.
     *
     * @return the Order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets order.
     *
     * @param order of item
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Gets product.
     *
     * @return the product of item
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets product for item.
     *
     * @param product of item
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Gets actial price of item.
     *
     * @return the price
     */
    public float getActualPrice() {
        return actualPrice;
    }

    /**
     * Sets actual price of item.
     *
     * @param actualPrice of item
     */
    public void setActualPrice(float actualPrice) {
        this.actualPrice = actualPrice;
    }

    /**
     * Gets quantity of items.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity of items.
     *
     * @param quantity of items
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets amount costs of items.
     *
     * @return the amount costs
     */
    public float getTotal() {
        return total;
    }

    /**
     * Sets amount costs of items
     *
     * @param total the category
     */
    public void setTotal(float total) {
        this.total = total;
    }

    public Integer getKey() {
        return itemId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Item other = (Item) object;
        return Objects.equals(getOrder(), other.getOrder())
                && getProduct().equals(other.getProduct())
                && getActualPrice() == other.getActualPrice()
                && getQuantity() == other.getQuantity()
                && getTotal() == other.getTotal();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getOrder(), getProduct(), getActualPrice(), getQuantity(), getTotal());
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + getKey() +
                ", order='" + getOrder() + '\'' +
                ", product=" + getProduct() +
                ", actualPrice=" + getActualPrice() +
                ", quantity=" + getQuantity() +
                ", total=" + getTotal() +
                '}';
    }
}
