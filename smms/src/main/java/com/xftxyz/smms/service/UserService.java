package com.xftxyz.smms.service;

import java.sql.Connection;
import java.util.Map;

import com.xftxyz.smms.dao.UserDao;
import com.xftxyz.smms.dao.impl.UserDaoImpl;
import com.xftxyz.smms.entity.User;
import com.xftxyz.smms.utils.CodeUtil;

import javafx.scene.image.Image;

public class UserService {
    private Connection conn;
    private UserDao ud = new UserDaoImpl();
    private String code = "";

    public UserService(Connection conn) {
        this.conn = conn;
    }

    // 生成验证码
    public Image getCode() {
        Map<String, Object> map = CodeUtil.get();
        code = (String) map.get("code");
        return (Image) map.get("image");
    }

    // 验证验证码
    public boolean checkCode(String code) {
        return this.code.equalsIgnoreCase(code);
    }

    // 登录成功返回user对象，失败返回null
    public User login(String username, String password) {
        if (username == null || username.equals("")) {
            return null;
        }

        if (password == null || password.equals("")) {
            return null;
        }

        return ud.getUser(conn, username, password);
    }

    // 添加用户
    // 修改密码
    // 修改权限
    // 删除用户
}
