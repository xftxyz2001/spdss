package com.xftxyz.smms.dao;

import java.sql.Connection;

import com.xftxyz.smms.entity.User;

public interface UserDao {
    // 根据User对象中的用户名和密码从数据库中获取一条记录
    User getUser(Connection conn, User user);

    void saveUser(Connection connection, User user);

    // 检查用户名是否存在
    // boolean checkName(Connection conn, String name);


}
