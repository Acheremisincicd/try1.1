package com.epam.preprod.biletska.dao;

import com.epam.preprod.biletska.entity.Category;
import com.epam.preprod.biletska.entity.Manufacturer;
import com.epam.preprod.biletska.entity.Product;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * The interface Product repository.
 */
public interface IProductDao extends IDao<Product>, IMapper<Product> {

    /**
     * Get all categories.
     *
     * @return list
     * @throws SQLException SQLException
     */
    List<Category> getAllCategories() throws SQLException;

    /**
     * Get all manufactures.
     *
     * @return list
     * @throws SQLException SQLException
     */
    List<Manufacturer> getAllManufacturers() throws SQLException;

    /**
     * Get all products by category.
     *
     * @param category : category
     * @return list
     * @throws SQLException SQLException
     */
    List<Product> getAllProductsByCategory(Category category) throws SQLException;

    /**
     * Get all products by manufacturer.
     *
     * @param manufacturer : manufacturer
     * @return list
     * @throws SQLException SQLException
     */
    List<Product> getAllProductsByManufacturer(Manufacturer manufacturer) throws SQLException;

    /**
     * Get all products by query.
     *
     * @param query : query
     * @return list
     * @throws SQLException SQLException
     */
    List<Product> getAllProductsByQuery(String query) throws SQLException;

    /**
     * Get all products on page by query.
     *
     * @param from  : from
     * @param to    : to
     * @param query : query
     * @return list
     * @throws SQLException SQLException
     */
    List<Product> getAllProductsOnPageByQuery(int from, int to, String query) throws SQLException;

    /**
     * Get all products by category and manufacturer.
     *
     * @param category     : category
     * @param manufacturer : manufacturer
     * @return list
     * @throws SQLException SQLException
     */
    List<Product> getAllProductsBy(Category category, Manufacturer manufacturer) throws SQLException;

    /**
     * Get all products by description.
     *
     * @param filterDescription : filter description
     * @return list
     * @throws SQLException SQLException
     */
    List<Product> filterProductsByDescription(String filterDescription) throws SQLException;

    /**
     * Get all products by price.
     *
     * @param filterPrice : filter price
     * @return list
     * @throws SQLException SQLException
     */
    List<Product> filterProductsByUpperPrice(float filterPrice) throws SQLException;

    /**
     * Get all products by date.
     *
     * @param filterDate : filter date
     * @return list
     * @throws SQLException SQLException
     */
    List<Product> filterProductsByDate(Date filterDate) throws SQLException;

    /**
     * Get all products for user.
     *
     * @param filterUser : filter user
     * @return list
     * @throws SQLException SQLException
     */
    List<Product> filterProductsByUserEmail(String filterUser) throws SQLException;
}
