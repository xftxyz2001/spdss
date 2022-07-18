package com.xftxyz.smms.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

import com.xftxyz.smms.dao.BaseDao;
import com.xftxyz.smms.dao.UserDao;
import com.xftxyz.smms.entity.User;
import com.xftxyz.smms.type.Role;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    public int addUser(Connection conn, User user) {
        String sql = "insert into users(name, pwd, role) values(?, ?, ?)";
        return insert(conn, sql, user.getName(), user.getPwd(), user.getRole().name());
    }

    @Override
    public boolean deleteUser(Connection conn, int id) {
        String sql = "delete from users where id = ?";
        return update(conn, sql, id) > 0;
    }

    @Override
    public boolean updateUser(Connection conn, User user) {
        String sql = "update users set name = ?, pwd = ?, role = ? where id = ?";
        return update(conn, sql, user.getName(), user.getPwd(), user.getRole().name(), user.getId()) > 0;
    }

    @Override
    public User getUser(Connection conn, String username, String password) {
        String sql = "select * from users where name = ? and pwd = ?";
        return getQuery(conn, sql, new ResultSetHandler<User>() {

            @Override
            public User handle(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setPwd(rs.getString("pwd"));
                    user.setRole(Role.valueOf(rs.getString("role")));
                    return user;
                }
                return null;
            }

        }, username, password);
    }

    @Override
    public List<User> getAllUsers(Connection conn) {
        String sql = "select * from users";
        return getQueryList(conn, sql, new ResultSetHandler<List<User>>() {

            @Override
            public List<User> handle(ResultSet rs) throws SQLException {
                List<User> users = new ArrayList<User>();
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setPwd(rs.getString("pwd"));
                    user.setRole(Role.valueOf(rs.getString("role")));
                    users.add(user);
                }
                return users;
            }

        });
    }

    @Override
    public boolean checkName(Connection conn, String name) {
        String sql = "select count(*) from users where name = ?";
        return (Long) getValue(conn, sql, name) > 0;
    }
}