package com.epam.preprod.biletska.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * The type Filter form bean.
 */
public class FilterFormDto {

    private String name;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private List<ManufacturerDto> listOfManufacturers;
    private List<CategoryDto> listOfCategories;

    /**
     * Instantiates a new Filter form bean.
     */
    public FilterFormDto() {
    }

    /**
     * Instantiates a new Filter form bean.
     *
     * @param name                the name
     * @param minPrice            the min price
     * @param maxPrice            the max price
     * @param listOfManufacturers the list of brands
     * @param listOfCategories    the list of categories
     */
    public FilterFormDto(String name, BigDecimal minPrice, BigDecimal maxPrice, List<ManufacturerDto> listOfManufacturers, List<CategoryDto> listOfCategories) {
        this.name = name;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.listOfManufacturers = listOfManufacturers;
        this.listOfCategories = listOfCategories;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets min price.
     *
     * @return the min price
     */
    public BigDecimal getMinPrice() {
        return minPrice;
    }

    /**
     * Sets min price.
     *
     * @param minPrice the min price
     */
    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * Gets max price.
     *
     * @return the max price
     */
    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    /**
     * Sets max price.
     *
     * @param maxPrice the max price
     */
    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * Gets list of brands.
     *
     * @return the list of brands
     */
    public List<ManufacturerDto> getListOfManufacturers() {
        return listOfManufacturers;
    }

    /**
     * Sets list of brands.
     *
     * @param listOfManufacturers the list of brands
     */
    public void setListOfManufacturers(List<ManufacturerDto> listOfManufacturers) {
        this.listOfManufacturers = listOfManufacturers;
    }

    /**
     * Gets list of categories.
     *
     * @return the list of categories
     */
    public List<CategoryDto> getListOfCategories() {
        return listOfCategories;
    }

    /**
     * Sets list of categories.
     *
     * @param listOfCategories the list of categories
     */
    public void setListOfCategories(List<CategoryDto> listOfCategories) {
        this.listOfCategories = listOfCategories;
    }

    public boolean isFilteringEnabled() {
        return !CommonDefinitions.isNullable(FilterFormDto.class, this);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        FilterFormDto that = (FilterFormDto) object;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getMinPrice(), that.getMinPrice()) &&
                Objects.equals(getMaxPrice(), that.getMaxPrice()) &&
                Objects.equals(getListOfManufacturers(), that.getListOfManufacturers()) &&
                Objects.equals(getListOfCategories(), that.getListOfCategories());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMinPrice(), getMaxPrice(), getListOfManufacturers(), getListOfCategories());
    }

    @Override
    public String toString() {
        return "FilterFormDto{" +
                "name='" + name + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", listOfManufacturers=" + listOfManufacturers +
                ", listOfCategories=" + listOfCategories +
                '}';
    }
}
