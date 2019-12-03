package com.epam.preprod.biletska.services;

import com.epam.preprod.biletska.dto.FilterFormDto;
import com.epam.preprod.biletska.dto.SortDto;
import com.epam.preprod.biletska.entity.Category;
import com.epam.preprod.biletska.entity.Manufacturer;
import com.epam.preprod.biletska.entity.Product;

import java.util.List;

/**
 * The interface User service.
 */
public interface IProductService extends IEntityService<Product>, ITransactionAware {

    List<Category> getAllCategories();

    List<Manufacturer> getAllManufacturers();

    List<Product> getProductsBy(Category category);

    List<Product> getProductsBy(Manufacturer manufacturer);

    List<Product> getProductsBy(Category category, Manufacturer manufacturer);

    List<Product> getAllProductsByCriteria(FilterFormDto filterFormDto);

    List<Product> getAllProductsOnPageByCriteria(int size, int page, FilterFormDto filterformDto, SortDto sort);

    int getNumberPages(int size, int page, FilterFormDto filterFormDto);

    int getNumberPages(int size, FilterFormDto filterFormDto);

    int getNumberPages(int size);
}


