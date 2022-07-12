package com.xftxyz.smms.entity;

import java.sql.Timestamp;

public class User {
    private int id;
    private String name;
    private String pwd;
    private String limits;
    private Timestamp createAt;

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

    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Users [createAt=" + createAt + ", id=" + id + ", limits=" + limits + ", name=" + name + ", pwd=" + pwd
                + "]";
    }

}
