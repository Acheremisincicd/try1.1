package com.epam.preprod.biletska.dto;

import java.util.Objects;

/**
 * The type Product DTO.
 */
public class ProductDto {

    private String name;
    private ManufacturerDto manufacturer;
    private CategoryDto category;
    private float price;

    /**
     * Instantiates a new Category DTO.
     */
    public ProductDto() {
    }

    public ManufacturerDto getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerDto manufacturer) {
        this.manufacturer = manufacturer;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductDto that = (ProductDto) o;
        return getName().equals(that.getName())
                && getCategory().equals(that.getCategory())
                && getManufacturer().equals(that.getManufacturer())
                && getPrice() == (that.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCategory().hashCode(), getManufacturer(), Float.valueOf(getPrice()).hashCode());
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "name='" + name + '\'' +
                ", category=" + getCategory() +
                ", manufacturer=" + getManufacturer() +
                ", price=" + getPrice() +
                '}';
    }
}
