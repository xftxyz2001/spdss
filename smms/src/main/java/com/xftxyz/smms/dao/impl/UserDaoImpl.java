package com.xftxyz.smms.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.dao.BaseDao;
import com.xftxyz.smms.dao.UserDao;
import com.xftxyz.smms.entity.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    public List<User> getAllUsers(Connection conn) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean checkName(Connection conn, String name) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<User> getUsers(Connection conn, String limits) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User getUser(Connection conn, String username, String password) {
        String sql = "select * from users where name = ? and pwd = ?";
        return getBean(conn, sql, username, password);
    }

    @Override
    public boolean saveUser(Connection connection, User user) {
        String sql = "insert into users(name, pwd, limits, createAt) values(?, ?, ?, ?)";
        return update(connection, sql, user.getName(), user.getPwd(), user.getLimits(), user.getCreateAt()) > 0;
    }

    @Override
    public boolean deleteUser(Connection conn, int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updatePassword(Connection conn, int id, String password) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateLimits(Connection conn, int id, String limits) {
        // TODO Auto-generated method stub
        return false;
    }

}
