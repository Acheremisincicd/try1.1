package com.epam.preprod.biletska.transaction;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Transaction dispatcher class.
 */
public class TransactionDispatcher {

    private DataSource dataSource;
    private final Logger LOGGER = Logger.getLogger(TransactionDispatcher.class);

    /**
     * Constructor for initializing TransactionDispatcher and init dataSource.
     */
    public TransactionDispatcher() {
        try {
            Context initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/ishop");
        } catch (NamingException e) {
            e.getMessage();
        }
    }

    /**
     * Execute current transaction.
     *
     * @param <T>          parameter.
     * @param ITransaction transaction.
     * @return T. t
     */
    public <T> T execute(ITransaction<T> ITransaction) {
        T result = null;
        try (Connection connection = dataSource.getConnection()) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            ConnectionHolder.setConnection(connection);
            result = ITransaction.execute();
            commit(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Connection connection = ConnectionHolder.getConnection();
            rollback(connection);
        } finally {
            ConnectionHolder.removeConnection();
        }
        return result;
    }

    private void commit(Connection connect) {
        try {
            if (connect != null) {
                connect.commit();
            }
        } catch (SQLException e) {
            LOGGER.warn("Connection lost, can't commit.");
        }
    }

    private void rollback(Connection connect) {
        try {
            if (connect != null) {
                connect.rollback();
            }
        } catch (SQLException e) {
            LOGGER.warn("Connection lost, can't'rollback");
        }
    }
}
