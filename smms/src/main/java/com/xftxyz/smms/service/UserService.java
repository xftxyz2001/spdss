package com.xftxyz.smms.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.xftxyz.smms.dao.UserDao;
import com.xftxyz.smms.dao.impl.UserDaoImpl;
import com.xftxyz.smms.entity.User;
import com.xftxyz.smms.type.Role;
import com.xftxyz.smms.utils.CodeUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class UserService {
    // 当前用户，通过login()方法设置
    private User currentUser;

    private String code = ""; // 此次生成的验证码
    private Connection conn; // 数据库连接对象，通过构造方法初始化
    // DAO对象
    private UserDao dao;
    // 观察列表
    private ObservableList<User> observableList;
    private User old;

    public UserService(Connection conn) {
        this.conn = conn;
        dao = new UserDaoImpl();
        initialize();
    }

    /**
     * 优化方向：保存过滤条件
     */
    private void initialize() {
        if (observableList == null) {
            observableList = FXCollections.observableArrayList();
        } else {
            observableList.clear();
        }
        List<User> users = dao.getAllUsers(conn);
        if (users != null) {
            observableList.addAll(users);
        }
    }

    public ObservableList<User> getObservableList() {
        return observableList;
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

        this.currentUser = dao.getUser(conn, username, password);
        return this.currentUser != null;
    }

    // 退出登录
    public void logout() {
        this.currentUser = null;
    }

    // 添加用户
    public boolean addUser(User user) {

        if (user == null) {
            return false; // 参数为空
        }

        if (dao.checkName(conn, user.getName())) {
            return false; // 用户名已存在
        }

        int id = dao.addUser(conn, user);
        if (id < 0) {
            return false; // 添加失败
        }
        user.setId(id);
        observableList.add(user);
        return true;
    }

    // 删除用户
    public boolean deleteUser(User user) {

        if (user == null) {
            return false;
        }

        boolean isSucc = dao.deleteUser(conn, user.getId());
        if (!isSucc) {
            return false;
        }
        observableList.remove(user);
        return true;
    }

    // 获取修改副本
    public User getUpdateCopy(User user) {
        if (user == null) {
            return null;
        }
        this.old = user;

        return user.copy();
    }

    // 修改用户
    public boolean updateUser(User newUser) {

        if (this.old == null) {
            return false;
        }
        if (newUser == null) {
            return false;
        }

        boolean isSucc = dao.updateUser(conn, newUser);
        if (!isSucc) {
            return false;
        }
        // observableList.remove(oldUser);
        // observableList.add(newUser);
        observableList.set(observableList.indexOf(this.old), newUser);
        return true;
    }

    // 获取当前用户名
    public String getCurrentUserName() {
        if (this.currentUser == null) {
            return "未获取到用户信息";
        }
        return this.currentUser.getName();
    }

    // 获取当前用户身份名
    public String getCurrentUserRoleName() {
        return getUserRoleName(this.currentUser);
    }

    // 获取当前用户身份
    public Role getCurrentUserRole() {
        return getUserRole(this.currentUser);
    }

    // 获取用户身份名
    public String getUserRoleName(User user) {
        if (user == null) {
            return "未获取到用户信息";
        }
        switch (getUserRole(user)) {
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

    // 获取用户身份
    public Role getUserRole(User user) {
        if (user == null) {
            return null;
        }
        return user.getRole();
    }

}
