package com.epam.preprod.biletska.dao.impl;

import com.epam.preprod.biletska.RepositoryConstants;
import com.epam.preprod.biletska.dao.IOrderDao;
import com.epam.preprod.biletska.entity.Order;
import com.epam.preprod.biletska.entity.OrderStatus;
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
 * * The implementation of UserDao interface
 */
public class OrderDao implements IOrderDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderDao.class);

    private static final String TOTAL = "total";
    private static final String ORDER_ID = "order_id";
    private static final String ORDER_DATE = "order_date";
    private static final String USER_EMAIL = "user_email";
    private static final String SHIP_DATE = "ship_date";
    private static final String STATUS = "status";
    private static final String STATUS_DETAILS = "status_details";

    @Override
    public Order get(String key) throws SQLException {
        int k = Integer.parseInt(key);
        return CommonUtils.getEntityByPreparedStatement(RepositoryConstants.QUERY_USER_ORDER_GET, (pst) -> {
            try {
                pst.setInt(1, k);
            } catch (SQLException e) {
                LOGGER.error("Error occurred {}", e.getMessage());
            }
        }, this);
    }

    @Override
    public List<Order> getAll() throws SQLException {
        return CommonUtils.getEntitiesForQuery(RepositoryConstants.QUERY_USER_ORDER_ALL, this);
    }

    @Override
    public Order create(Order order) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(RepositoryConstants.QUERY_USER_ORDER_INSERT, Statement.RETURN_GENERATED_KEYS);
        getPreparedForInsert(pst, order).executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            order.setOrderId(rs.getInt(1));
        }
        return order;
    }

    @Override
    public void update(Order order) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(RepositoryConstants.QUERY_USER_ORDER_UPDATE);
        getPreparedForUpdate(pst, order).executeUpdate();
    }

    @Override
    public void delete(Order order) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(RepositoryConstants.QUERY_USER_ORDER_DELETE);
        getPreparedForDelete(pst, order).executeUpdate();
    }

    @Override
    public Order mapRow(ResultSet rs) throws SQLException {
        if (rs.isBeforeFirst() && !rs.next()) {
            return null;
        }
        Order order = new Order();
        order.setOrderId(rs.getInt(ORDER_ID));
        order.setOrderDate(rs.getDate(ORDER_DATE));
        order.setUserEmail(rs.getString(USER_EMAIL));
        order.setShipDate(rs.getDate(SHIP_DATE));
        order.setTotal(rs.getFloat(TOTAL));
        order.setStatus(OrderStatus.getByValue(rs.getString(STATUS)));
        order.setStatusDetails(rs.getString(STATUS_DETAILS));
        return order;
    }

    private PreparedStatement getPreparedForInsert(PreparedStatement pst, Order order) throws SQLException {
        int i = 1;
        pst.setDate(i++, new java.sql.Date(order.getOrderDate().getTime()));
        pst.setString(i++, order.getUserEmail());
        pst.setDate(i++, new java.sql.Date(order.getShipDate().getTime()));
        pst.setFloat(i++, order.getTotal());
        pst.setString(i++, order.getStatus().getValue());
        pst.setString(i++, order.getStatusDetails());
        return pst;
    }

    private PreparedStatement getPreparedForUpdate(PreparedStatement pst, Order order) throws SQLException {
        pst = getPreparedForInsert(pst, order);
        pst.setInt(7, order.getOrderId());
        return pst;
    }

    private PreparedStatement getPreparedForDelete(PreparedStatement pst, Order order) throws SQLException {
        int i = 1;
        pst.setInt(i++, order.getOrderId());
        return pst;
    }
}