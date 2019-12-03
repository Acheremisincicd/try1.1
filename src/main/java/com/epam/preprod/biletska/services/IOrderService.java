package com.epam.preprod.biletska.services;

import com.epam.preprod.biletska.entity.Item;
import com.epam.preprod.biletska.entity.Order;

import java.util.List;

/**
 * The interface User service.
 */
public interface IOrderService extends IEntityService<Order>, ITransactionAware {

    /**
     * Get the order.
     *
     * @param order order
     * @return order
     */
    List<Item> getOrderItems(Order order);

    /**
     * Add item to the order.
     *
     * @param item item
     * @return item
     */
    Item addItemToOrder(Item item);

    /**
     * Remove item from the order.
     *
     * @param item item
     */
    void removeItemFromOrder(Item item);

    /**
     * Cancel the order.
     *
     * @param order  order
     * @param reason reason
     */
    void cancelOrder(Order order, String reason);

    /**
     * Create order.
     *
     * @param order order
     */
    void checkoutOrder(Order order);
}
