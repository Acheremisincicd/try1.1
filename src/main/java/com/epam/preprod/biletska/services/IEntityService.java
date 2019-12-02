package com.epam.preprod.biletska.services;

import java.util.List;

/**
 * The interface Entity service.
 */
public interface IEntityService<T> {
    /**
     * get Entity by key method
     *
     * @param key entity's key
     * @return entity
     */
    T get(String key);

    /**
     * get all entities
     *
     * @return entities' list
     */
    List<T> getAll();

    /**
     * create entity
     *
     * @param t parameter
     * @return entity
     */
    T create(T t);

    /**
     * update entity
     *
     * @param t parameter
     */
    void update(T t);

    /**
     * delete entity
     *
     * @param t parameter
     */
    void delete(T t);
}
