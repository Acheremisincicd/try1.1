package com.epam.preprod.biletska.dao.impl;

import com.epam.preprod.biletska.RepositoryConstants;
import com.epam.preprod.biletska.dao.ICategoryDao;
import com.epam.preprod.biletska.entity.Category;
import com.epam.preprod.biletska.transaction.ConnectionHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Category DAO implementation.
 */
public class CategoryDao implements ICategoryDao {

    private static final String CATEGORY_ID = "category_id";
    private final static Logger LOGGER = LoggerFactory.getLogger(CategoryDao.class);
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";

    @Override
    public Category get(String key) throws SQLException {
        return CommonUtils.getEntityByPreparedStatement(RepositoryConstants.QUERY_PRODUCT_CATEGORY_GET, (pst) -> {
            try {
                int k = Integer.parseInt(key);
                pst.setInt(1, k);
            } catch (SQLException e) {
                LOGGER.error("Error occurred {}", e.getMessage());
            }
        }, this);
    }

    @Override
    public List<Category> getAll() throws SQLException {
        return CommonUtils.getEntitiesForQuery(RepositoryConstants.QUERY_PRODUCT_CATEGORY_ALL, this);
    }

    @Override
    public Category create(Category category) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(RepositoryConstants.QUERY_PRODUCT_CATEGORY_INSERT);
        getPreparedForInsert(pst, category).executeUpdate();
        return category;
    }

    @Override
    public void update(Category category) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(RepositoryConstants.QUERY_PRODUCT_CATEGORY_DELETE);
        getPreparedForUpdate(pst, category).executeUpdate();
    }

    @Override
    public void delete(Category category) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(RepositoryConstants.QUERY_PRODUCT_CATEGORY_UPDATE);
        getPreparedForDelete(pst, category).executeUpdate();
    }

    @Override
    public Category mapRow(ResultSet rs) throws SQLException {
        if (rs.isBeforeFirst() && !rs.next()) {
            return null;
        }

        Category category = new Category();
        category.setCategoryId(rs.getInt(CATEGORY_ID));
        category.setName(rs.getString(NAME));
        category.setDescription(rs.getString(DESCRIPTION));
        return category;
    }

    @Override
    public List<Category> filterCategoriesByName(String filterName) throws SQLException {
        return CommonUtils.getEntitiesByPreparedStatement(RepositoryConstants.PRODUCT_CATEGORY_BY_NAME, (pst) -> {
            try {
                pst.setString(1, filterName);
            } catch (SQLException e) {
                LOGGER.error("Error occurred {}", e.getMessage());
            }
        }, this);
    }

    @Override
    public List<Category> filterCategoriesByDescription(String filterName) throws SQLException {
        return CommonUtils.getEntitiesByPreparedStatement(RepositoryConstants.PRODUCT_CATEGORY_BY_DESCRIPTION, (pst) -> {
            try {
                pst.setString(1, filterName);
            } catch (SQLException e) {
                LOGGER.error("Error occurred {}", e.getMessage());
            }
        }, this);
    }

    private PreparedStatement getPreparedForInsert(PreparedStatement pst, Category category) throws SQLException {
        int i = 1;
        pst.setString(i++, category.getName());
        pst.setString(i++, category.getDescription());
        return pst;
    }

    private PreparedStatement getPreparedForUpdate(PreparedStatement pst, Category category) throws SQLException {
        int i = 1;
        pst.setString(i++, category.getName());
        pst.setString(i++, category.getDescription());
        pst.setInt(i++, category.getKey());
        return pst;
    }

    private PreparedStatement getPreparedForDelete(PreparedStatement pst, Category category) throws SQLException {
        int i = 1;
        pst.setInt(i++, category.getCategoryId());
        return pst;
    }
}
