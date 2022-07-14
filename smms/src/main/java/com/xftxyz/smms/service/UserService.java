package com.xftxyz.smms.service;

import java.sql.Connection;
import java.util.Map;

import com.xftxyz.smms.dao.UserDao;
import com.xftxyz.smms.dao.impl.UserDaoImpl;
import com.xftxyz.smms.entity.User;
import com.xftxyz.smms.type.Limits;
import com.xftxyz.smms.utils.CodeUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class UserService {
    private Connection conn; // 数据库连接对象，通过构造方法初始化

    // DAO对象
    private UserDao ud;
    // 观察列表
    private ObservableList<User> olUsers;

    private String code = ""; // 此次生成的验证码

    // 当前用户，通过login()方法设置
    private User user;

    public UserService(Connection conn) {
        this.conn = conn;
        ud = new UserDaoImpl();
        initializeUserObservableList();
    }

    private void initializeUserObservableList() {
        olUsers = FXCollections.observableArrayList();
        olUsers.addAll(ud.getAllUsers(conn));
    }

    public ObservableList<User> getUserObservableList() {
        return olUsers;
    }

    // 生成验证码
    public Image getCode() {
        Map<String, Object> map = CodeUtil.get();
        this.code = (String) map.get("code");
        return (Image) map.get("image");
    }

    // 验证验证码
    public boolean checkCode(String code) {
        return this.code.equalsIgnoreCase(code);
    }

    // 登录，初始化当前用户
    public boolean login(String username, String password) {
        if (username == null || username.trim().equals("")) {
            return false;
        }

        if (password == null || password.trim().equals("")) {
            return false;
        }

        this.user = ud.getUser(conn, username, password);
        return this.user != null;
    }

    // 获取当前用户
    public User getUser() {
        return this.user;
    }

    // 退出登录
    public void logout() {
        this.user = null;
    }

    // 添加用户
    public boolean addUser(User user) {
        // if (this.user == null) {
        // return false; // 没登陆
        // }

        // if (Limits.hasLimit(user, o)){return false;} // 权限不足
        // if (user.getUsername() == null || user.getUsername().trim().equals("")) {
        // return false;
        // }

        // if (user.getPassword() == null || user.getPassword().trim().equals("")) {
        // return false;
        // }

        boolean isSucc = ud.saveUser(conn, user);
        if (!isSucc) {
            // olUsers.remove(user);
            return false;
        }
        olUsers.add(user);
        return true;
    }

    // 删除用户
    public boolean deleteUser(User user) {

        boolean isSucc = ud.deleteUser(conn, user.getId());
        if (!isSucc) {
            // olUsers.remove(user);
            return false;
        }
        olUsers.remove(user);
        return true;
    }

    // 修改密码
    // 修改权限

    // 获取当前在线用户
    public User getCurrentUser() {
        return this.user;
    }

    // 获取当前用户名
    public String getCurrentUserName() {
        if (this.user == null) {
            return "未获取到用户信息";
        }
        return this.user.getName();
    }

    // 获取当前用户身份
    public String getCurrentUserRole() {
        if (this.user == null) {
            return "未获取到用户信息";
        }
        switch (Limits.valueOf(this.user.getLimits())) {
            case ADMIN:
                return "管理员";
            case MANAGER:
                return "经理";
            case PURCHASER:
                return "采购员";
            case SALER:
                return "销售员";
            default:
                break;
        }
        return "未知身份";
    }

}
