package com.epam.preprod.biletska.services.impl;

import com.epam.preprod.biletska.dao.impl.ProductDao;
import com.epam.preprod.biletska.dao.impl.QueryBuilder;
import com.epam.preprod.biletska.dto.FilterFormDto;
import com.epam.preprod.biletska.dto.SortDto;
import com.epam.preprod.biletska.entity.Category;
import com.epam.preprod.biletska.entity.Manufacturer;
import com.epam.preprod.biletska.entity.Product;
import com.epam.preprod.biletska.services.AbstractIEntityService;
import com.epam.preprod.biletska.services.IProductService;
import com.epam.preprod.biletska.transaction.ITransaction;
import com.epam.preprod.biletska.transaction.TransactionDispatcher;

import java.util.List;

/**
 * Service for working with user repository and processing data.
 */
public class ProductService extends AbstractIEntityService<Product, ProductDao> implements IProductService {

    public ProductService(ProductDao dao, TransactionDispatcher transactionDispatcher) {
        super(dao, transactionDispatcher);
    }

    @Override
    public List<Category> getAllCategories() {
        ITransaction<List<Category>> transaction = () -> dao.getAllCategories();
        return getTransactionDispatcher().execute(transaction);
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        ITransaction<List<Manufacturer>> transaction = () -> dao.getAllManufacturers();
        return getTransactionDispatcher().execute(transaction);
    }

    @Override
    public List<Product> getProductsBy(Category category) {
        ITransaction<List<Product>> transaction = () -> dao.getAllProductsByCategory(category);
        return getTransactionDispatcher().execute(transaction);
    }

    @Override
    public List<Product> getProductsBy(Manufacturer manufacturer) {
        ITransaction<List<Product>> transaction = () -> dao.getAllProductsByManufacturer(manufacturer);
        return getTransactionDispatcher().execute(transaction);
    }

    @Override
    public List<Product> getProductsBy(Category category, Manufacturer manufacturer) {
        if (category == null) {
            return getProductsBy(manufacturer);
        } else if (manufacturer == null) {
            return getProductsBy(category);
        } else {
            ITransaction<List<Product>> transaction = () -> dao.getAllProductsByCategory(category);
            return getTransactionDispatcher().execute(transaction);
        }
    }

    @Override
    public List<Product> getAllProductsByCriteria(FilterFormDto filterFormDto) {
        String query = new QueryBuilder(filterFormDto).getQueryForCount();
        return getTransactionDispatcher().execute(() -> dao.getAllProductsByQuery(query));
    }

    @Override
    public List<Product> getAllProductsOnPageByCriteria(int size, int page, FilterFormDto filterformDto, SortDto sort) {
        String query = new QueryBuilder(filterformDto, sort).getQuery();
        int startNumber = transfersNumberToFrom(size, page);
        return getTransactionDispatcher().execute(() -> dao.getAllProductsOnPageByQuery(startNumber, size, query));
    }

    @Override
    public int getNumberPages(int size, int page, FilterFormDto filterFormDto) {
        List<Product> products = getAllProductsByCriteria(filterFormDto);
        return products.size() / size + 1;
    }

    @Override
    public int getNumberPages(int size, FilterFormDto filterFormDto) {
        List<Product> products = getAllProductsByCriteria(filterFormDto);
        return products.size() / size;
    }

    @Override
    public int getNumberPages(int size) {
        return getAll().size() / size;
    }

    private int transfersNumberToFrom(int size, int page) {
        int currentPage = page == 0 ? 1 : page;
        return page >= 2 ? (currentPage - 1) * size : 0;
    }
}
