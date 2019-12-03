package com.epam.preprod.biletska.dto;

import java.util.Objects;
import java.util.stream.Stream;

public final class CommonDefinitions {

    private CommonDefinitions() {
    }

    public static <T> boolean isNullable(Class<T> clazz, T obj) {
        return Stream.of(clazz.getDeclaredFields()).map(e -> {
            boolean defaultAccessible = e.isAccessible();
            try {
                e.setAccessible(true);
                return e.get(obj);
            } catch (Exception ex) {
            } finally {
                e.setAccessible(defaultAccessible);
            }
            return null;
        }).allMatch(Objects::isNull);
    }
}
