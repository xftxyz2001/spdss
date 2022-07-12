package com.xftxyz.smms.entity;

public class Supplier {
    private int id; // 供应商编号
    private String name; // 供应商名称
    private String address; // 地址
    private String phone; // 联系方式

    public Supplier() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Supplier [address=" + address + ", id=" + id + ", name=" + name + ", phone=" + phone + "]";
    }

}
