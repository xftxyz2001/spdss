package com.xftxyz.smms.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.dao.BaseDao;
import com.xftxyz.smms.dao.UserDao;
import com.xftxyz.smms.entity.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    public List<User> getAllUsers(Connection conn) {
        String sql = "select * from users";
        return getBeanList(conn, sql);
    }

    @Override
    public boolean checkName(Connection conn, String name) {
        String sql = "select * from users where name = ?";
        return getBean(conn, sql, name) != null;
    }

    @Override
    public List<User> getUsers(Connection conn, String limits) {
        String sql = "select * from users where limits = ?";
        return getBeanList(conn, sql, limits);
    }

    @Override
    public User getUser(Connection conn, String username, String password) {
        String sql = "select * from users where name = ? and pwd = ?";
        return getBean(conn, sql, username, password);
    }

    @Override
    public int saveUser(Connection conn, User user) {
        String sql = "insert into users(name, pwd, limits, createAt) values(?, ?, ?, ?)";
        return insert(conn, sql, user.getName(), user.getPwd(), user.getLimits(), user.getCreateAt());
    }

    @Override
    public boolean deleteUser(Connection conn, int id) {
        String sql = "delete from users where id = ?";
        return update(conn, sql, id) > 0;
    }

    @Override
    public boolean updatePassword(Connection conn, int id, String password) {
        String sql = "update users set pwd = ? where id = ?";
        return update(conn, sql, password, id) > 0;
    }

    @Override
    public boolean updateLimits(Connection conn, int id, String limits) {
        String sql = "update users set limits = ? where id = ?";
        return update(conn, sql, limits, id) > 0;
    }

}
