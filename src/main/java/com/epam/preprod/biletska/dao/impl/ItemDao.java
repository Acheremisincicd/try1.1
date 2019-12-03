package com.epam.preprod.biletska.dao.impl;

import com.epam.preprod.biletska.RepositoryConstants;
import com.epam.preprod.biletska.dao.IItemDao;
import com.epam.preprod.biletska.entity.Item;
import com.epam.preprod.biletska.entity.Order;
import com.epam.preprod.biletska.transaction.ConnectionHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * * The implementation of ItemDao interface
 */
public class ItemDao implements IItemDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(ItemDao.class);

    //private static final String ITEM_ID = "item_id";
    private static final String ORDER_ID = "order_id";
    private static final String PRODUCT_ID = "product_id";
    private static final String ACTUAL_PRICE = "actual_price";
    private static final String QUANTITY = "quantity";
    private static final String TOTAL = "total";

    private ProductDao productDao;
    private OrderDao orderDao;

    public ItemDao(ProductDao productDao, OrderDao orderDao) {
        this.productDao = productDao;
        this.orderDao = orderDao;
    }

    @Override
    public Item get(String key) throws SQLException {
        return CommonUtils.getEntityByPreparedStatement(RepositoryConstants.QUERY_ORDER_ITEM_GET, (pst) -> {
            try {
                int k = Integer.parseInt(key);
                pst.setInt(1, k);
            } catch (SQLException e) {
                LOGGER.error("Error occurred {}", e.getMessage());
            }
        }, this);
    }

    @Override
    public List<Item> getAll() throws SQLException {
        return CommonUtils.getEntitiesForQuery(RepositoryConstants.QUERY_ORDER_ITEM_ALL, this);
    }

    @Override
    public Item create(Item item) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(RepositoryConstants.QUERY_ORDER_ITEM_INSERT, Statement.RETURN_GENERATED_KEYS);
        getPreparedForInsert(pst, item).executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            item.setItemId(rs.getInt(1));
        }
        return item;
    }

    @Override
    public void update(Item item) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(RepositoryConstants.QUERY_ORDER_ITEM_UPDATE);
        getPreparedForUpdate(pst, item).executeUpdate();
    }

    @Override
    public void delete(Item item) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(RepositoryConstants.QUERY_ORDER_ITEM_DELETE);
        getPreparedForDelete(pst, item).executeUpdate();
    }


    @Override
    public Item mapRow(ResultSet rs) throws SQLException {
        if (rs.isBeforeFirst() && !rs.next()) {
            return null;
        }
        Item item = new Item();
        item.setOrder(orderDao.get(rs.getString(ORDER_ID)));
        item.setProduct(productDao.get(rs.getString(PRODUCT_ID)));
        item.setActualPrice(rs.getFloat(ACTUAL_PRICE));
        item.setQuantity((rs.getInt(QUANTITY)));
        item.setTotal(rs.getFloat(TOTAL));
        return item;
    }

    private PreparedStatement getPreparedForInsert(PreparedStatement pst, Item item) throws SQLException {
        int i = 1;
        pst.setInt(i++, item.getOrder().getKey());
        pst.setInt(i++, item.getProduct().getKey());
        pst.setFloat(i++, item.getActualPrice());
        pst.setInt(i++, item.getQuantity());
        pst.setFloat(i++, item.getTotal());
        return pst;
    }

    private PreparedStatement getPreparedForUpdate(PreparedStatement pst, Item item) throws SQLException {
        pst = getPreparedForInsert(pst, item);
        pst.setInt(6, item.getKey());
        return pst;
    }

    private PreparedStatement getPreparedForDelete(PreparedStatement pst, Item item) throws SQLException {
        int i = 1;
        pst.setInt(i++, item.getKey());
        return pst;
    }

    @Override
    public List<Item> getItemsForOrder(Order order) throws SQLException {
        return CommonUtils.getEntitiesByPreparedStatement(RepositoryConstants.QUERY_ORDER_ITEM_ITEMS_FOR_ORDER, (pst) -> {
            try {
                pst.setInt(1, order.getKey());
            } catch (SQLException e) {
                LOGGER.error("Error occurred {}", e.getMessage());
            }
        }, this);
    }
}