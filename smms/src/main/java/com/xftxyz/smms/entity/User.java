package com.xftxyz.smms.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.xftxyz.smms.type.Role;

/**
 * 用户类
 */
public class User {
    @ExcelProperty("用户编号")
    private int id; // 用户编号X
    @ExcelProperty("用户名")
    private String name; // 用户名
    @ExcelProperty("密码")
    private String pwd; // 密码
    @ExcelProperty("角色")
    private Role role; // 身份

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (pwd == null) {
            if (other.pwd != null)
                return false;
        } else if (!pwd.equals(other.pwd))
            return false;
        if (role != other.role)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", pwd=" + pwd + ", role=" + role + "]";
    }

    public User copy() {
        User c = new User();
        c.setId(id);
        c.setName(name);
        c.setPwd(pwd);
        c.setRole(role);
        return c;
    }

}
