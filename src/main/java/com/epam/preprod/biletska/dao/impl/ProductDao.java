package com.epam.preprod.biletska.dao.impl;

import com.epam.preprod.biletska.RepositoryConstants;
import com.epam.preprod.biletska.dao.IProductDao;
import com.epam.preprod.biletska.entity.Category;
import com.epam.preprod.biletska.entity.Manufacturer;
import com.epam.preprod.biletska.entity.Product;
import com.epam.preprod.biletska.transaction.ConnectionHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ProductDao implements IProductDao {

    private static final String CATEGORY_ID = "category_id";
    private final static Logger LOGGER = LoggerFactory.getLogger(ProductDao.class);
    private static final String PRODUCT_ID = "product_id";
    private static final String DESCRIPTION = "description";
    private static final String MANUFACTURER_ID = "manufacturer_id";
    private static final String LIST_PRICE = "list_price";
    private CategoryDao categoryDao;
    private ManufacturerDao manufacturerDao;

    public ProductDao(CategoryDao categoryDao, ManufacturerDao manufacturerDao) {
        this.categoryDao = categoryDao;
        this.manufacturerDao = manufacturerDao;
    }

    @Override
    public Product get(String key) throws SQLException {
        return CommonUtils.getEntityByPreparedStatement(RepositoryConstants.QUERY_PRODUCTS_GET, (pst) -> {
            try {
                int k = Integer.parseInt(key);
                pst.setInt(1, k);
            } catch (SQLException e) {
                LOGGER.error("Error occurred {}", e.getMessage());
            }
        }, this);
    }

    @Override
    public List<Product> getAll() throws SQLException {
        return CommonUtils.getEntitiesForQuery(RepositoryConstants.QUERY_PRODUCTS_ALL, this);
    }

    @Override
    public Product create(Product product) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(RepositoryConstants.QUERY_PRODUCTS_INSERT);
        getPreparedForInsert(pst, product).executeUpdate();
        return product;
    }

    @Override
    public void update(Product product) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(RepositoryConstants.QUERY_PRODUCTS_UPDATE);
        getPreparedForUpdate(pst, product).executeUpdate();
    }

    @Override
    public void delete(Product product) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(RepositoryConstants.QUERY_PRODUCTS_DELETE);
        getPreparedForDelete(pst, product).executeUpdate();
    }

    @Override
    public List<Category> getAllCategories() throws SQLException {
        return CommonUtils.getEntitiesForQuery(RepositoryConstants.QUERY_PRODUCT_CATEGORY_ALL, categoryDao);
    }

    @Override
    public List<Manufacturer> getAllManufacturers() throws SQLException {
        return CommonUtils.getEntitiesForQuery(RepositoryConstants.QUERY_PRODUCT_MANUFACTURER_ALL, manufacturerDao);
    }

    @Override
    public List<Product> getAllProductsByCategory(Category category) throws SQLException {
        return CommonUtils.getEntitiesByPreparedStatement(RepositoryConstants.QUERY_PRODUCTS_BY_CATEGORY, (pst) -> {
            try {
                pst.setInt(1, category.getKey());
            } catch (SQLException e) {
                LOGGER.error("Error occurred {}", e.getMessage());
            }
        }, this);
    }

    @Override
    public List<Product> getAllProductsByManufacturer(Manufacturer manufacturer) throws SQLException {
        return CommonUtils.getEntitiesByPreparedStatement(RepositoryConstants.QUERY_PRODUCTS_BY_MANUFACTURER, (pst) -> {
            try {
                pst.setInt(1, manufacturer.getKey());
            } catch (SQLException e) {
                LOGGER.error("Error occurred {}", e.getMessage());
            }
        }, this);
    }

    @Override
    public List<Product> getAllProductsByQuery(String query) throws SQLException {
        return CommonUtils.getEntitiesForQuery(query, this);
    }

    @Override
    public List<Product> getAllProductsOnPageByQuery(int from, int to, String query) throws SQLException {
        return CommonUtils.getEntitiesByPreparedStatement(query, (pst) -> {
            try {
                pst.setInt(1, from);
                pst.setInt(2, to);
            } catch (SQLException e) {
                LOGGER.error("Error occurred {}", e.getMessage());
            }
        }, this);
    }

    @Override
    public List<Product> getAllProductsBy(Category category, Manufacturer manufacturer) throws SQLException {
        return CommonUtils.getEntitiesByPreparedStatement(RepositoryConstants.QUERY_PRODUCTS_BY_CATEGORY_AND_MANUFACTURER, (pst) -> {
            try {
                pst.setInt(1, category.getKey());
                pst.setInt(2, manufacturer.getKey());
            } catch (SQLException e) {
                LOGGER.error("Error occurred {}", e.getMessage());
            }
        }, this);
    }

    @Override
    public List<Product> filterProductsByDescription(String filterDescription) throws SQLException {
        return CommonUtils.getEntitiesByPreparedStatement(RepositoryConstants.QUERY_PRODUCTS_BY_DESCRIPTION, (pst) -> {
            try {
                pst.setString(1, "%" + filterDescription + "%");
            } catch (SQLException e) {
                LOGGER.error("Error occurred {}", e.getMessage());
            }
        }, this);
    }

    @Override
    public List<Product> filterProductsByUpperPrice(float filterPrice) throws SQLException {
        return CommonUtils.getEntitiesByPreparedStatement(RepositoryConstants.QUERY_PRODUCTS_BY_UPPER_PRICE, (pst) -> {
            try {
                pst.setFloat(1, filterPrice);
            } catch (SQLException e) {
                LOGGER.error("Error occurred {}", e.getMessage());
            }
        }, this);
    }

    @Override
    public List<Product> filterProductsByDate(Date filterDate) throws SQLException {
        return CommonUtils.getEntitiesByPreparedStatement(RepositoryConstants.QUERY_PRODUCTS_BY_ORDER_DATE, (pst) -> {
            try {
                pst.setDate(1, new java.sql.Date(filterDate.getTime()));
            } catch (SQLException e) {
                LOGGER.error("Error occurred {}", e.getMessage());
            }
        }, this);
    }

    @Override
    public List<Product> filterProductsByUserEmail(String filterUserEmail) throws SQLException {
        return CommonUtils.getEntitiesByPreparedStatement(RepositoryConstants.QUERY_PRODUCTS_BY_USER_EMAIL_ORDER, (pst) -> {
            try {
                pst.setString(1, filterUserEmail);
            } catch (SQLException e) {
                LOGGER.error("Error occurred {}", e.getMessage());
            }
        }, this);
    }

    @Override

    public Product mapRow(ResultSet rs) throws SQLException {
        if (rs.isBeforeFirst() && !rs.next()) {
            return null;
        }
        Product product = new Product();
        product.setProductId(rs.getInt(PRODUCT_ID));
        product.setDescription(rs.getString(DESCRIPTION));
        product.setCategory(categoryDao.get(String.valueOf(rs.getInt(CATEGORY_ID))));
        product.setManufacturer(manufacturerDao.get(String.valueOf(rs.getInt(MANUFACTURER_ID))));
        product.setPrice((rs.getBigDecimal(LIST_PRICE)));
        return product;
    }

    private PreparedStatement getPreparedForInsert(PreparedStatement pst, Product product) throws SQLException {
        int i = 1;
        pst.setString(i++, product.getDescription());
        pst.setInt(i++, product.getCategory().getCategoryId());
        return pst;
    }

    private PreparedStatement getPreparedForUpdate(PreparedStatement pst, Product product) throws SQLException {
        int i = 1;
        pst.setString(i++, product.getDescription());
        pst.setInt(i++, product.getCategory().getCategoryId());
        pst.setInt(i++, product.getProductId());
        return pst;
    }

    private PreparedStatement getPreparedForDelete(PreparedStatement pst, Product product) throws SQLException {
        int i = 1;
        pst.setInt(i++, product.getProductId());
        return pst;
    }
}
