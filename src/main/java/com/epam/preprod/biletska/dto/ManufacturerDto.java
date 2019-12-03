package com.epam.preprod.biletska.dto;

import java.util.Objects;

/**
 * The type Manufacturer DTO.
 */
public class ManufacturerDto {

    private String name;
    private String description;
    private boolean value;

    /**
     * Instantiates a new Manufacturer DTO.
     */
    public ManufacturerDto() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Instantiates a new Manufacturer DTO.
     *
     * @param name  the name
     * @param value the value
     */
    public ManufacturerDto(String name, String description, boolean value) {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ManufacturerDto manufacturerDto = (ManufacturerDto) o;
        return isValue() == manufacturerDto.isValue() && Objects.equals(getName(), manufacturerDto.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), isValue());
    }

    @Override
    public String toString() {
        return "ManufacturerDto{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
