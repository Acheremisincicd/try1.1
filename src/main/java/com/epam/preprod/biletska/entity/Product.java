package com.epam.preprod.biletska.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private int productId;
    private String description;
    private Category category;
    private Manufacturer manufacturer;
    private BigDecimal price;

    /**
     * Instantiates a new Product.
     */
    public Product() {
    }

    /**
     * Instantiates a new Product.
     *
     * @param productId   the id
     * @param description the description
     * @param category    the category
     */
    public Product(int productId, String description, Category category) {
        this.productId = productId;
        this.description = description;
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getKey() {
        return productId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Product product = (Product) object;
        return Objects.equals(getDescription(), product.getDescription()) &&
                getCategory().equals(product.getCategory()) && getManufacturer().equals(product.getManufacturer())
                && getPrice().equals(product.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getCategory());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + getProductId() +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", manufacturer=" + manufacturer +
                ", price=" + price +
                '}';
    }
}
