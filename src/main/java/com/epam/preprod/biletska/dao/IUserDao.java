package com.epam.preprod.biletska.dao;

import com.epam.preprod.biletska.entity.User;

import java.sql.SQLException;

/**
 * The interface User repository.
 */
public interface IUserDao extends IDao<User>, IMapper<User> {

    /**
     * Gets user by login.
     *
     * @param login : the login
     * @return the user by login
     */
    User findByLogin(String login) throws SQLException;
}
