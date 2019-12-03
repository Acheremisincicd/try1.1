package com.epam.preprod.biletska.entity;

import java.util.Objects;

public class Manufacturer {

    private int manufacturerId;
    private String name;
    private String description;

    public Manufacturer() {
    }

    public Manufacturer(int manufacturerId, String name) {
        this.manufacturerId = manufacturerId;
        this.name = name;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getKey() {
        return manufacturerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Manufacturer that = (Manufacturer) o;
        return manufacturerId == that.manufacturerId &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturerId, name, description);
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "manufacturerId=" + manufacturerId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
