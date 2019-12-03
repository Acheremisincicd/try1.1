package com.epam.preprod.biletska.dao.impl;

import com.epam.preprod.biletska.dto.CategoryDto;
import com.epam.preprod.biletska.dto.FilterFormDto;
import com.epam.preprod.biletska.dto.ManufacturerDto;
import com.epam.preprod.biletska.dto.SortDirection;
import com.epam.preprod.biletska.dto.SortDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class for building the SQL query.
 */
public class QueryBuilder {

    private final static Logger LOGGER = LoggerFactory.getLogger(QueryBuilder.class);
    private static final String QUERY = "SELECT * FROM products " +
            "LEFT JOIN category ON products.category_id = category.category_id " +
            "LEFT JOIN manufacturer ON products.manufacturer_id = manufacturer.manufacturer_id " +
            "LEFT JOIN price ON products.product_id = price.product_id AND SYSDATE() BETWEEN price.start_date AND price.end_date WHERE TRUE ";
    private static final String LIMIT = " LIMIT ?,?";
    private static final String QUERY_FOR_COUNT = "SELECT * FROM products " +
            "LEFT JOIN category ON products.category_id = category.category_id " +
            "LEFT JOIN manufacturer ON products.manufacturer_id = manufacturer.manufacturer_id " +
            "LEFT JOIN price ON products.product_id = price.product_id AND SYSDATE() BETWEEN price.start_date AND price.end_date WHERE TRUE ";
    private FilterFormDto filterFormDto;
    private SortDto sort;

    /**
     * Instantiates a new Query builder.
     *
     * @param filterFormDto the filter form bean
     */
    public QueryBuilder(FilterFormDto filterFormDto) {
        this.filterFormDto = filterFormDto;
        sort = null;
    }

    /**
     * Instantiates a new Query builder.
     *
     * @param filterFormDto the filter form bean
     * @param sort          the sort
     */
    public QueryBuilder(FilterFormDto filterFormDto, SortDto sort) {
        this.filterFormDto = filterFormDto;
        this.sort = sort;
    }

    /**
     * Gets query.
     *
     * @return the query
     */
    public String getQuery() {
        StringBuilder query = new StringBuilder(QUERY);
        doFilters(query);
        sortFilter(query);
        query.append(LIMIT);
        LOGGER.info("Query for executing: {}", query.toString());
        return query.toString();
    }

    /**
     * Gets query for count the pages.
     *
     * @return the query for count
     */
    public String getQueryForCount() {
        StringBuilder query = new StringBuilder(QUERY_FOR_COUNT);
        doFilters(query);
        sortFilter(query);
        return query.toString();
    }

    private void doFilters(StringBuilder query) {
        if (filterFormDto == null) {
            return;
        }
        nameFilter(query);
        priceFilter(query);
        categoryFilter(query);
        manufacturerFilter(query);
    }

    private void nameFilter(StringBuilder sb) {
        if (filterFormDto.getName() == null) {
            return;
        }
        sb.append(" AND products.description LIKE '%" + filterFormDto.getName() + "%'");
    }

    private void priceFilter(StringBuilder sb) {
        if (filterFormDto.getMinPrice() == null) {
            return;
        }
        sb.append(" AND IFNULL(price.list_price,1) > " + filterFormDto.getMinPrice().floatValue() +
                " AND IFNULL(price.list_price,1) < " + filterFormDto.getMaxPrice().floatValue());
    }

    private void categoryFilter(StringBuilder sb) {
        if (filterFormDto.getListOfCategories() == null) {
            return;
        }
        boolean checkCategory = false;
        List<CategoryDto> categoryList = filterFormDto.getListOfCategories();
        for (CategoryDto categoryDto : categoryList) {
            if (categoryDto.isValue()) {
                checkCategory = true;
                break;
            }
        }
        if (checkCategory) {
            sb.append(" AND category.name IN ('");
            for (CategoryDto categoryDto : categoryList) {
                if (categoryDto.isValue()) {
                    sb.append(categoryDto.getName() + "' ,'");
                }
            }
            sb.replace(sb.length() - 2, sb.length(), ")");
        }
    }

    private void manufacturerFilter(StringBuilder sb) {
        if (filterFormDto.getListOfManufacturers() == null) {
            return;
        }
        boolean checkManufacturer = false;
        List<ManufacturerDto> manufacturerList = filterFormDto.getListOfManufacturers();
        for (ManufacturerDto manufacturerDto : manufacturerList) {
            if (manufacturerDto.isValue()) {
                checkManufacturer = true;
                break;
            }
        }
        if (checkManufacturer) {
            sb.append(" AND manufacturer.name IN ('");
            for (ManufacturerDto manufacturerDto : manufacturerList) {
                if (manufacturerDto.isValue()) {
                    sb.append(manufacturerDto.getName() + "' ,'");
                }
            }
            sb.replace(sb.length() - 2, sb.length(), ")");
        }
    }

    private void sortFilter(StringBuilder sb) {
        if (sort == null) {
            return;
        }
        switch (sort.getField()) {
            case "name":
                sb.append(" ORDER BY products.description ");
                break;
            case "category":
                sb.append(" ORDER BY category.name ");
                break;
            case "manufacturer":
                sb.append(" ORDER BY manufacturer.name ");
                break;
            case "price":
                sb.append(" ORDER BY price.list_price ");
                break;
        }
        if (!sort.getDirection().equals(SortDirection.NONE)) {
            sb.append(sort.getDirection().name());
        }
    }
}
