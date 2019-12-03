package com.epam.preprod.biletska.dao.impl;

import com.epam.preprod.biletska.dao.IMapper;
import com.epam.preprod.biletska.transaction.ConnectionHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CommonUtils {

    private CommonUtils() {
    }

    public static <T, M extends IMapper<T>> List<T> getEntitiesForQuery(String query, M mapper) throws SQLException {
        List<T> listOfEntities = new ArrayList<>();
        Connection conn = ConnectionHolder.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            listOfEntities.add(mapper.mapRow(rs));
        }
        return listOfEntities;
    }

    public static <T, M extends IMapper<T>> List<T> getEntitiesByPreparedStatement(String query, Consumer<PreparedStatement> prepareConsumer, M mapper) throws SQLException {
        List<T> listOfEntities = new ArrayList<>();
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(query);
        prepareConsumer.accept(pst);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            listOfEntities.add(mapper.mapRow(rs));
        }
        return listOfEntities;
    }

    public static <T, M extends IMapper<T>> T getEntityByPreparedStatement(String query, Consumer<PreparedStatement> prepareConsumer, M mapper) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(query);
        prepareConsumer.accept(pst);
        ResultSet rs = pst.executeQuery();
        return mapper.mapRow(rs);
    }
}
