package com.epam.preprod.biletska.dao;

import com.epam.preprod.biletska.entity.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * The interface User repository.
 */
public interface ICategoryDao extends IDao<Category>, IMapper<Category> {

    /**
     * Method for filter by name.
     *
     * @param filterName : Filter name
     * @return list
     * @throws SQLException SQLException
     */
    List<Category> filterCategoriesByName(String filterName) throws SQLException;

    /**
     * Method for filter by description.
     *
     * @param filterName : Filter name
     * @return list
     * @throws SQLException SQLException
     */
    List<Category> filterCategoriesByDescription(String filterName) throws SQLException;

    /**
     * Method for get category by key.
     *
     * @param key : key
     * @return category
     * @throws SQLException SQLException
     */
    public Category get(String key) throws SQLException;
}
