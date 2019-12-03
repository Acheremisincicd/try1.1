package com.epam.preprod.biletska.entity;

import java.util.Date;
import java.util.Objects;

/**
 * Order of the list of products.
 */
public class Order {

    private int orderId;
    private Date orderDate;
    private String userEmail;
    private Date shipDate;
    private float total;
    private OrderStatus status;
    private String statusDetails;


    /**
     * Instantiates a new Order.
     */
    public Order() {
    }

    /**
     * Instantiates a new Order.
     *
     * @param orderDate date of creation
     * @param userEmail user identifier
     * @param shipDate  date of order shipping
     * @param total     order amount price
     */
    public Order(Date orderDate, String userEmail, Date shipDate, float total, OrderStatus status, String statusDetails) {
        this.orderDate = orderDate;
        this.userEmail = userEmail;
        this.shipDate = shipDate;
        this.total = total;
        this.status = status;
        this.statusDetails = statusDetails;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Integer getKey() {
        return orderId;
    }

    public String getStatusDetails() {
        return statusDetails;
    }

    public void setStatusDetails(String statusDetails) {
        this.statusDetails = statusDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return getOrderId() == order.getOrderId() &&
                Objects.equals(getOrderDate(), order.getOrderDate()) &&
                getUserEmail().equals(order.getUserEmail()) &&
                Objects.equals(getOrderDate(), order.getOrderDate()) &&
                getTotal() == order.getTotal();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getOrderDate(), getUserEmail(), getShipDate(), getTotal());
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + getOrderDate() +
                ", userEmail=" + getUserEmail() +
                ", shipDate=" + getShipDate() +
                ", total=" + getTotal() +
                '}';
    }
}

