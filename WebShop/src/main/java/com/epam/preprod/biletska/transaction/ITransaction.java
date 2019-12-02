package com.epam.preprod.biletska.transaction;

import java.sql.SQLException;

/**
 * Transaction class.
 *
 * @param <T> parameter
 */
public interface ITransaction<T> {

    /**
     * Execute current transaction operation.
     *
     * @return parameter T.
     * @throws SQLException the sql exception
     */
    T execute() throws SQLException;
}
