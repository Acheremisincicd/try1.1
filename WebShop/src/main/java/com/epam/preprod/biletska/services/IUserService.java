package com.epam.preprod.biletska.services;

import com.epam.preprod.biletska.entity.User;

/**
 * The interface User service.
 */
public interface IUserService extends IEntityService<User>, ITransactionAware {

    /**
     * Gets by login.
     *
     * @param login the login
     * @return the by login
     */
    User getUserByLogin(String login);

    /**
     * Add user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    User addUser(User user);
}