package com.epam.preprod.biletska.dao;

import com.epam.preprod.biletska.entity.Manufacturer;

import java.sql.SQLException;
import java.util.List;

/**
 * Manufacturer Dao interface.
 */
public interface IManufacturerDao extends IDao<Manufacturer>, IMapper<Manufacturer> {

    /**
     * Method for filter manufacturers by name.
     *
     * @param filterName : filter name
     * @return list
     * @throws SQLException SQLException
     */
    List<Manufacturer> filterManufacturersByName(String filterName) throws SQLException;

    /**
     * Method for filter manufacturers by description.
     *
     * @param filterName : filterName
     * @return list
     * @throws SQLException SQLException
     */
    List<Manufacturer> filterManufacturersByDescription(String filterName) throws SQLException;
}
