package com.epam.preprod.biletska;

/**
 * Repository constants class.
 */
public final class RepositoryConstants {

    /**
     * Utility class doesn't allowed to instantiate it
     */
    private RepositoryConstants() {
    }

    /**
     * The constant ID.
     */
    //public final static String ID = "id";

    /**
     * The constant USER_FIRST_NAME.
     */
    public final static String USER_FIRST_NAME = "first_name";

    /**
     * The constant USER_LAST_NAME.
     */
    public final static String USER_LAST_NAME = "last_name";

    /**
     * The constant USER_LOGIN.
     */
    //  public final static String USER_LOGIN = "login";

    /**
     * The constant USER_EMAIL.
     */
    public final static String USER_EMAIL = "email";

    /**
     * The constant USER_PASSWORD.
     */
    public final static String USER_PASSWORD = "password";

    /**
     * The constant USER_MAILING.
     */
    public final static String USER_MAILING = "subscribe";

    /**
     * The constant USER_MAILING.
     */
    public final static String USER_ROLE = "role";

    /**
     * The constant QUERY_USER_INSERT.
     */
    public final static String QUERY_USER_INSERT = "INSERT INTO user (first_name, last_name, email, password, subscribe, role) VALUES(?,?,?,?,?,?)";

    public final static String QUERY_USER_GET = "SELECT * FROM user WHERE email=?";
    public final static String QUERY_USER_ALL = "SELECT * FROM user";
    public final static String QUERY_USER_UPDATE = "UPDATE USER SET first_name=?, last_name=?, email=?, password=?, subscribe=? WHERE id=?";
    public final static String QUERY_USER_DELETE = "DELETE FROM USER WHERE email=?";

    /**
     * The constant USER_SELECT_LOGIN.
     */
    public final static String USER_SELECT_LOGIN = "SELECT * FROM user WHERE email = ?";

    /**
     * Products related SQL
     */
    private final static String QUERY_PRODUCT_GET_BASE = "SELECT * FROM products\n" +
            " LEFT JOIN price\n" +
            "  ON products.product_id = price.product_id AND SYSDATE() BETWEEN price.start_date AND price.end_date\n" +
            " LEFT JOIN category ON products.category_id = category.category_id\n" +
            " LEFT JOIN manufacturer ON products.manufacturer_id = manufacturer.manufacturer_id WHERE TRUE";
    public final static String QUERY_PRODUCTS_GET = QUERY_PRODUCT_GET_BASE + " AND products.    product_id = ?";
    public final static String QUERY_PRODUCTS_ALL = QUERY_PRODUCT_GET_BASE;
    public final static String QUERY_PRODUCTS_INSERT = "INSERT INTO products(description, category_id, manufacturer_id) VALUES(?, ?, ?)";
    public final static String QUERY_PRODUCTS_DELETE = "DELETE FROM products WHERE product_id = ?";
    public final static String QUERY_PRODUCTS_UPDATE = "UPDATE products SET description = ?, category_id = ?, manufacturer_id WHERE product_id = ?";

    public final static String QUERY_PRODUCTS_BY_CATEGORY = "SELECT * FROM products WHERE category_id = ?";
    public final static String QUERY_PRODUCTS_BY_MANUFACTURER = "SELECT * FROM products WHERE manufacturer_id = ?";
    public final static String QUERY_PRODUCTS_BY_CATEGORY_AND_MANUFACTURER = "SELECT * FROM products WHERE category_id = ? AND manufacturer_id = ?";
    public final static String QUERY_PRODUCTS_BY_DESCRIPTION = "SELECT * FROM products WHERE LOWER(description) LIKE LOWER(?)";
    public final static String QUERY_PRODUCTS_BY_UPPER_PRICE = "SELECT p.* FROM products p\n" +
            "JOIN price pr ON p.product_id = pr.product_id\n" +
            "WHERE SYSDATE() BETWEEN pr.start_date AND pr.end_date AND pr.list_price <= ?";
    public final static String QUERY_PRODUCTS_BY_ORDER_DATE = "SELECT p.* FROM products p\n" +
            "JOIN items i ON p.product_id = i.product_id\n" +
            "JOIN orders o ON i.order_id = o.order_id\n" +
            "WHERE DATE_FORMAT(o.order_date, '%Y-%m-%d') = DATE_FORMAT(?, '%Y-%m-%d')";
    public final static String QUERY_PRODUCTS_BY_USER_EMAIL_ORDER = "SELECT p.* FROM products p\n" +
            "JOIN items i ON p.product_id = i.product_id\n" +
            "JOIN orders o ON i.order_id = o.order_id\n" +
            "WHERE LOWER(o.user_email) = LOWER(?)";


    /**
     * Product category SQL
     */
    public final static String QUERY_PRODUCT_CATEGORY_GET = "SELECT * FROM category WHERE category_id = ?";
    public final static String QUERY_PRODUCT_CATEGORY_ALL = "SELECT * FROM category";
    public final static String QUERY_PRODUCT_CATEGORY_INSERT = "INSERT INTO category(name, description) VALUES(?, ?)";
    public final static String QUERY_PRODUCT_CATEGORY_DELETE = "DELETE FROM category WHERE category_id = ?";
    public final static String QUERY_PRODUCT_CATEGORY_UPDATE = "UPDATE category SET name = ?, description=? WHERE category_id = ?";

    public final static String PRODUCT_CATEGORY_BY_NAME = "SELECT * FROM category WHERE LOWER(name) LIKE LOWER(?)";
    public final static String PRODUCT_CATEGORY_BY_DESCRIPTION = "SELECT * FROM category WHERE LOWER(description) LIKE LOWER(?)";

    /**
     * Product manufacturer SQL
     */
    public final static String QUERY_PRODUCT_MANUFACTURER_GET = "SELECT * FROM manufacturer WHERE manufacturer_id = ?";
    public final static String QUERY_PRODUCT_MANUFACTURER_ALL = "SELECT * FROM manufacturer";
    public final static String QUERY_PRODUCT_MANUFACTURER_INSERT = "INSERT INTO manufacturer(name, description) VALUES(?, ?)";
    public final static String QUERY_PRODUCT_MANUFACTURER_DELETE = "DELETE FROM manufacturer WHERE manufacturer_id = ?";
    public final static String QUERY_PRODUCT_MANUFACTURER_UPDATE = "UPDATE manufacturer SET name=?, description=? WHERE manufacturer_id = ?";

    public final static String QUERY_PRODUCT_MANUFACTURER_BY_NAME = "SELECT * FROM manufacturer WHERE LOWER(name) LIKE LOWER(?)";
    public final static String QUERY_PRODUCT_MANUFACTURER_BY_DESCRIPTION = "SELECT * FROM manufacturer WHERE LOWER(description) LIKE LOWER(?)";

    /**
     * Order related SQL
     */
    public final static String QUERY_USER_ORDER_GET = "SELECT * FROM orders WHERE order_id = ?";
    public final static String QUERY_USER_ORDER_ALL = "SELECT * FROM orders";
    public final static String QUERY_USER_ORDER_INSERT = "INSERT INTO orders(order_date, user_email, ship_date, total, status, status_details) VALUES(?, ?, ?, ?, ?, ?)";
    public final static String QUERY_USER_ORDER_DELETE = "DELETE FROM orders WHERE order_id = ?";
    public final static String QUERY_USER_ORDER_UPDATE = "UPDATE orders SET order_date = ?, user_email = ?, ship_date = ?, total = ?, status = ?, status_details = ? WHERE order_id = ?";

    /**
     * Item related SQL
     */
    public final static String QUERY_ORDER_ITEM_GET = "SELECT * FROM items WHERE item_id = ?";
    public final static String QUERY_ORDER_ITEM_ALL = "SELECT * FROM items";
    public final static String QUERY_ORDER_ITEM_INSERT = "INSERT INTO items(order_id, product_id, actual_price, quantity, total) VALUES(?, ?, ?, ?, ?)";
    public final static String QUERY_ORDER_ITEM_DELETE = "DELETE FROM items WHERE item_id = ?";
    public final static String QUERY_ORDER_ITEM_UPDATE = "UPDATE items SET order_id = ?, product_id = ?, actual_price = ?, quantity = ?, total = ? WHERE items_id = ?";

    public final static String QUERY_ORDER_ITEM_ITEMS_FOR_ORDER = "SELECT items.* FROM items " +
            "JOIN orders ON orders.order_id = items.order_id AND orders.order_id = ?";

}
