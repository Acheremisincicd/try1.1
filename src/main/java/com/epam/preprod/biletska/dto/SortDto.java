package com.epam.preprod.biletska.dto;

import java.util.Objects;

/**
 * Sort dto.
 */
public class SortDto {

    private String field;
    private SortDirection direction;

    public SortDto(String field) {
        this.field = field;
        direction = SortDirection.ASC;
    }

    public SortDto(String field, SortDirection direction) {
        this.field = field;
        this.direction = direction;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public SortDirection getDirection() {
        return direction;
    }

    public void setDirection(SortDirection direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SortDto that = (SortDto) o;
        return Objects.equals(getField(), that.getField()) &&
                Objects.equals(getDirection(), that.getDirection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getField(), getDirection());
    }

    @Override
    public String toString() {
        return "SortDto{" +
                "field='" + field + '\'' +
                ", direction=" + direction +
                '}';
    }
}
