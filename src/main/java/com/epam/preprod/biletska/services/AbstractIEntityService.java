package com.epam.preprod.biletska.services;

import com.epam.preprod.biletska.dao.IDao;
import com.epam.preprod.biletska.transaction.ITransaction;
import com.epam.preprod.biletska.transaction.TransactionDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * The AbstractEntityService.
 */
public abstract class AbstractIEntityService<T, R extends IDao<T>> implements IEntityService<T>, ITransactionAware {

    final static Logger logger = LoggerFactory.getLogger(AbstractIEntityService.class);

    private TransactionDispatcher transactionDispatcher;
    protected R dao;

    public AbstractIEntityService(R dao, TransactionDispatcher transactionDispatcher) {
        this.dao = dao;
        this.transactionDispatcher = transactionDispatcher;
    }

    @Override
    public TransactionDispatcher getTransactionDispatcher() {
        return transactionDispatcher;
    }

    @Override
    public T get(String key) {
        ITransaction<T> ITransaction = () -> dao.get(key);
        return getTransactionDispatcher().execute(ITransaction);
    }

    @Override
    public List<T> getAll() {
        ITransaction<List<T>> ITransaction = () -> dao.getAll();
        return getTransactionDispatcher().execute(ITransaction);
    }

    @Override
    public T create(T t) {
        ITransaction<T> ITransaction = () -> dao.create(t);
        return getTransactionDispatcher().execute(ITransaction);
    }

    @Override
    public void update(T t) {
        ITransaction<Optional<T>> ITransaction = () -> {
            dao.update(t);
            return Optional.empty();
        };
        getTransactionDispatcher().execute(ITransaction);
    }

    @Override
    public void delete(T t) {
        ITransaction<T> ITransaction = () -> {
            dao.delete(t);
            return null;
        };
        getTransactionDispatcher().execute(ITransaction);
    }
}
