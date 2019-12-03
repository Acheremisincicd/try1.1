package com.epam.preprod.biletska.dto;

import java.util.Objects;

/**
 * The type Category DTO.
 */
public class CategoryDto {

    private String name;
    private String description;
    private boolean value;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Instantiates a new Category DTO.
     */
    public CategoryDto() {
    }

    /**
     * Instantiates a new Category DTO.
     *
     * @param name  the name
     * @param value the value
     */
    public CategoryDto(String name, String description, boolean value) {
        this.name = name;
        this.description = description;
        this.value = value;
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
     * Is value boolean.
     *
     * @return the boolean
     */
    public boolean isValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        CategoryDto that = (CategoryDto) object;
        return isValue() == that.isValue() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), isValue());
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
