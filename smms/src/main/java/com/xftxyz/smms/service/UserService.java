package com.xftxyz.smms.service;

import java.sql.Connection;
import java.util.Map;

import com.xftxyz.smms.dao.UserDao;
import com.xftxyz.smms.dao.impl.UserDaoImpl;
import com.xftxyz.smms.entity.User;
import com.xftxyz.smms.exception.ExIllegalParameter;
import com.xftxyz.smms.exception.ExNotLogin;
import com.xftxyz.smms.utils.CodeUtil;

import javafx.scene.image.Image;

public class UserService {
    private Connection conn; // 数据库连接对象，通过构造方法初始化

    // DAO对象
    private UserDao ud = new UserDaoImpl();

    private String code = ""; // 此次生成的验证码

    // 当前用户，通过login()方法设置
    private User user;

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

    // 登录，初始化当前用户
    public boolean login(String username, String password) throws ExIllegalParameter {
        if (username == null || username.trim().equals("")) {
            throw new ExIllegalParameter("用户名为空");
        }

        if (password == null || password.trim().equals("")) {
            throw new ExIllegalParameter("密码为空");
        }

        this.user = ud.getUser(conn, username, password);
        return this.user != null;
    }

    // 退出登录
    public void logout() {
        this.user = null;
    }

    // 添加用户
    public boolean addUser(User user) throws ExNotLogin {
        if (user == null) {
            throw new ExNotLogin("用户未登录");
        }

        // TODO 判断权限

        return ud.saveUser(conn, user);
    }

    // 修改密码
    // 修改权限
    // 删除用户
}
