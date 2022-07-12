package com.xftxyz.smms.dao;

import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.entity.User;

public interface UserDao {
    // 根据User对象中的用户名和密码从数据库中获取一条记录
    User getUser(Connection conn, User user);

    // 获取所有用户
    List<User> getAllUsers(Connection conn);

    // 新增一个用户
    void saveUser(Connection connection, User user);

    // 检查用户名是否存在
    boolean checkName(Connection conn, String name);

    // 根据限制返回用户列表
    List<User> getUsers(Connection conn, String limits);

    // 根据用户编号删除用户

    // 修改密码

    // 修改权限
}
