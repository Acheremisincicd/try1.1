package com.epam.preprod.biletska.dao.impl;

import com.epam.preprod.biletska.RepositoryConstants;
import com.epam.preprod.biletska.dao.IUserDao;
import com.epam.preprod.biletska.entity.User;
import com.epam.preprod.biletska.transaction.ConnectionHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * * The implementation of UserDao interface
 */
public class UserDao implements IUserDao {

    private PreparedStatement getPreparedForInsert(PreparedStatement pst, User user) throws SQLException {
        int i = 1;
        pst.setString(i++, user.getFirstName());
        pst.setString(i++, user.getLastName());
        pst.setString(i++, user.getEmail());
        pst.setString(i++, user.getPassword());
        pst.setBoolean(i++, user.isMailingEnabled());
        pst.setString(i, user.getRole());
        return pst;
    }

    private PreparedStatement getPreparedForUpdate(PreparedStatement pst, User user) throws SQLException {
        return getPreparedForInsert(pst, user);
    }

    @Override
    public User findByLogin(String login) throws SQLException {
        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement pst = connection.prepareStatement(RepositoryConstants.USER_SELECT_LOGIN);
        pst.setString(1, login);
        ResultSet rs = pst.executeQuery();
        return mapRow(rs);
    }

    @Override
    public User get(String key) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(RepositoryConstants.QUERY_USER_GET);
        pst.setString(1, key);
        ResultSet rs = pst.executeQuery();
        return mapRow(rs);
    }

    @Override
    public List getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = ConnectionHolder.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(RepositoryConstants.QUERY_USER_ALL);
        while (rs.next()) {
            users.add(mapRow(rs));
        }
        return users;
    }

    @Override
    public User create(User user) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(RepositoryConstants.QUERY_USER_INSERT);
        getPreparedForInsert(pst, user).executeUpdate();
        return user;
    }

    @Override
    public void update(User user) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(RepositoryConstants.QUERY_USER_UPDATE);
        getPreparedForUpdate(pst, user).executeUpdate();
    }

    @Override
    public void delete(User user) throws SQLException {
        Connection conn = ConnectionHolder.getConnection();
        PreparedStatement pst = conn.prepareStatement(RepositoryConstants.QUERY_USER_DELETE);
        getPreparedForUpdate(pst, user).executeUpdate();
    }

    @Override
    public User mapRow(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return null;
        }
        User user = new User();
        user.setFirstName(rs.getString(RepositoryConstants.USER_FIRST_NAME));
        user.setLastName(rs.getString(RepositoryConstants.USER_LAST_NAME));
        user.setEmail(rs.getString(RepositoryConstants.USER_EMAIL));
        user.setPassword(rs.getString(RepositoryConstants.USER_PASSWORD));
        user.setMailingEnabled(rs.getBoolean(RepositoryConstants.USER_MAILING));
        user.setRole(rs.getString(RepositoryConstants.USER_ROLE));
        return user;
    }
}
