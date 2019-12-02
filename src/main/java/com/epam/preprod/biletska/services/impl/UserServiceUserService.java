package com.epam.preprod.biletska.services.impl;

import com.epam.preprod.biletska.dao.impl.UserDao;
import com.epam.preprod.biletska.entity.User;
import com.epam.preprod.biletska.services.AbstractIEntityService;
import com.epam.preprod.biletska.services.IUserService;
import com.epam.preprod.biletska.transaction.ITransaction;
import com.epam.preprod.biletska.transaction.TransactionDispatcher;

/**
 * Service for working with user repository and processing data.
 */
public class UserServiceUserService extends AbstractIEntityService<User, UserDao> implements IUserService {

    public UserServiceUserService(UserDao dao, TransactionDispatcher transactionDispatcher) {
        super(dao, transactionDispatcher);
    }

    @Override
    public User addUser(User user) {
        return create(user);
    }

    @Override
    public User getUserByLogin(String login) {
        ITransaction<User> ITransaction = () -> dao.
                findByLogin(login);
        return getTransactionDispatcher().execute(ITransaction);
    }
}
