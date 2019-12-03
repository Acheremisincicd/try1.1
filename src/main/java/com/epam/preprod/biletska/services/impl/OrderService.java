package com.epam.preprod.biletska.services.impl;

import com.epam.preprod.biletska.dao.IItemDao;
import com.epam.preprod.biletska.dao.impl.OrderDao;
import com.epam.preprod.biletska.entity.Item;
import com.epam.preprod.biletska.entity.Order;
import com.epam.preprod.biletska.entity.OrderStatus;
import com.epam.preprod.biletska.services.AbstractIEntityService;
import com.epam.preprod.biletska.services.IOrderService;
import com.epam.preprod.biletska.transaction.ITransaction;
import com.epam.preprod.biletska.transaction.TransactionDispatcher;

import java.util.List;

/**
 * Service for working with orders and items
 */
public class OrderService extends AbstractIEntityService<Order, OrderDao> implements IOrderService {

    private final IItemDao IItemDao;

    public OrderService(OrderDao dao, IItemDao IItemDao, TransactionDispatcher transactionDispatcher) {
        super(dao, transactionDispatcher);
        this.IItemDao = IItemDao;
    }

    @Override
    public List<Item> getOrderItems(Order order) {
        ITransaction<List<Item>> transaction = () -> IItemDao.getItemsForOrder(order);
        return getTransactionDispatcher().execute(transaction);
    }

    @Override
    public Item addItemToOrder(Item item) {
        ITransaction<Item> transaction = () -> IItemDao.create(item);
        return getTransactionDispatcher().execute(transaction);
    }

    @Override
    public void removeItemFromOrder(Item item) {
        ITransaction<Void> transaction = () -> {
            IItemDao.delete(item);
            return null;
        };
        getTransactionDispatcher().execute(transaction);
    }

    @Override
    public void cancelOrder(Order order, String reason) {
        order.setStatus(OrderStatus.CANCELED);
        order.setStatusDetails(reason);
        update(order);
    }

    @Override
    public void checkoutOrder(Order order) {
        order.setStatus(OrderStatus.COMPLETED);
        order.setStatusDetails("Order has been completed");
        update(order);
    }
}
