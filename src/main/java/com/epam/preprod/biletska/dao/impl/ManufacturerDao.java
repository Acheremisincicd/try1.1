package com.epam.preprod.biletska.dao.impl;

import com.epam.preprod.biletska.RepositoryConstants;
import com.epam.preprod.biletska.dao.IManufacturerDao;
import com.epam.preprod.biletska.entity.Manufacturer;
import com.epam.preprod.biletska.transaction.ConnectionHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Manufacturer DAO implementation.
 */
public class ManufacturerDao implements IManufacturerDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(ManufacturerDao.class);
    private static final String MANUFACTURER_ID = "manufacturer_id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";

    @Override
    public Manufacturer get(String key) throws SQLException {
        return CommonUtils.getEntityByPreparedStatement(RepositoryConstants.QUERY_PRODUCT_MANUFACTURER_GET, (pst) -> {
            try {
                int k = Integer.parseInt(key);
                pst.setInt(1, k);
            } catch (SQLException e) {
                LOGGER.error("Error occurred {}", e.getMessage());
            }
        }, this);
    }

    @Override
    public List<Manufacturer> getAll() throws SQLException {
        return CommonUtils.getEntitiesForQuery(RepositoryConstants.QUERY_PRODUCT_MANUFACTURER_ALL, this);
    }

    @Override
    public Manufacturer create(Manufacturer manufacturer) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(RepositoryConstants.QUERY_PRODUCT_MANUFACTURER_INSERT);
        getPreparedForInsert(pst, manufacturer).executeUpdate();
        return manufacturer;
    }

    @Override
    public void update(Manufacturer manufacturer) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(RepositoryConstants.QUERY_PRODUCT_MANUFACTURER_DELETE);
        getPreparedForUpdate(pst, manufacturer).executeUpdate();
    }

    @Override
    public void delete(Manufacturer manufacturer) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(RepositoryConstants.QUERY_PRODUCT_MANUFACTURER_UPDATE);
        getPreparedForDelete(pst, manufacturer).executeUpdate();
    }

    @Override
    public Manufacturer mapRow(ResultSet rs) throws SQLException {
        if (rs.isBeforeFirst() && !rs.next()) {
            return null;
        }
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setManufacturerId(rs.getInt(MANUFACTURER_ID));
        manufacturer.setName(rs.getString(NAME));
        manufacturer.setDescription(rs.getString(DESCRIPTION));
        return manufacturer;
    }

    @Override
    public List<Manufacturer> filterManufacturersByName(String filterName) throws SQLException {
        return CommonUtils.getEntitiesByPreparedStatement(RepositoryConstants.QUERY_PRODUCT_MANUFACTURER_BY_NAME, (pst) -> {
            try {
                pst.setString(1, filterName);
            } catch (SQLException e) {
                LOGGER.error("Error occurred {}", e.getMessage());
            }
        }, this);
    }

    @Override
    public List<Manufacturer> filterManufacturersByDescription(String filterName) throws SQLException {
        return CommonUtils.getEntitiesByPreparedStatement(RepositoryConstants.QUERY_PRODUCT_MANUFACTURER_BY_DESCRIPTION, (pst) -> {
            try {
                pst.setString(1, filterName);
            } catch (SQLException e) {
                LOGGER.error("Error occurred {}", e.getMessage());
            }
        }, this);
    }

    private PreparedStatement getPreparedForInsert(PreparedStatement pst, Manufacturer manufacturer) throws SQLException {
        int i = 1;
        pst.setString(i++, manufacturer.getName());
        pst.setString(i++, manufacturer.getDescription());
        return pst;
    }

    private PreparedStatement getPreparedForUpdate(PreparedStatement pst, Manufacturer manufacturer) throws SQLException {
        int i = 1;
        pst.setString(i++, manufacturer.getName());
        pst.setString(i++, manufacturer.getDescription());
        pst.setInt(i++, manufacturer.getKey());
        return pst;
    }

    private PreparedStatement getPreparedForDelete(PreparedStatement pst, Manufacturer manufacturer) throws SQLException {
        int i = 1;
        pst.setInt(i++, manufacturer.getKey());
        return pst;
    }
}
