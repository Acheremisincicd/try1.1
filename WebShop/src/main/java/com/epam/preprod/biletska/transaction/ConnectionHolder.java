package com.epam.preprod.biletska.transaction;

import java.sql.Connection;

/**
 * Connection Holder class.
 */
public class ConnectionHolder {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    /**
     * Get connection from ThreadLocal.
     *
     * @return Connection. connection
     */
    public static Connection getConnection() {
        return threadLocal.get();
    }

    /**
     * Set connection to ThreadLocal.
     *
     * @param connection connection
     */
    public static void setConnection(Connection connection) {
        threadLocal.set(connection);
    }

    /**
     * remove connection from ThreadLocal.
     */
    public static void removeConnection() {
        threadLocal.remove();
    }
}
