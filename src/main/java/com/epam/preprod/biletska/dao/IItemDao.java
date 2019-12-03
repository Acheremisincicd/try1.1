package com.epam.preprod.biletska.dao;

import com.epam.preprod.biletska.entity.Item;
import com.epam.preprod.biletska.entity.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * The interface Item repository.
 */
public interface IItemDao extends IDao<Item>, IMapper<Item> {

    List<Item> getItemsForOrder(Order order) throws SQLException;
}
