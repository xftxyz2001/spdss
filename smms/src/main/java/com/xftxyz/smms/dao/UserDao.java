package com.xftxyz.smms.dao;

import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.entity.User;

public interface UserDao {
    // 新增一个用户，返回id
    int addUser(Connection conn, User user);

    // 根据用户编号删除用户
    boolean deleteUser(Connection conn, int id);

    // 根据用户编号更新用户信息
    boolean updateUser(Connection conn, User user);

    // 获取所有用户
    List<User> getAllUsers(Connection conn);

    // 根据User对象中的用户名和密码从数据库中获取一条记录
    User getUser(Connection conn, String username, String password);

    // 检查用户名是否存在
    boolean checkName(Connection conn, String name);

}
