package com.epam.preprod.biletska.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapper for processing result set from database.
 *
 * @param <T> parameter.
 */
public interface IMapper<T> {

    /**
     * Map result set to specific object T
     *
     * @param rs result set
     * @return filled object
     * @throws SQLException when can not map row.
     */
    T mapRow(ResultSet rs) throws SQLException;
}