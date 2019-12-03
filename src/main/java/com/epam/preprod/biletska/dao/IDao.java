package com.epam.preprod.biletska.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * DAO interface.
 *
 * @param <T> param
 */
public interface IDao<T> {

    /**
     * Get one operation
     */
    T get(String key) throws SQLException;

    /**
     * Get all operation
     */
    List<T> getAll() throws SQLException;

    /**
     * Create operation
     */
    T create(T t) throws SQLException;

    /**
     * Update operation
     */
    void update(T t) throws SQLException;

    /**
     * Delete operation
     */
    void delete(T t) throws SQLException;
}
