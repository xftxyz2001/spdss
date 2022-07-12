package com.xftxyz.smms.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Sale {
    private int id; // 销售单编号
    private int goodsId; // 商品编号
    private BigDecimal price; // 销售价
    private BigDecimal number; // 销售数量
    private String unit; // 单位
    // private String remark;
    private Timestamp createdAt; // 销售时间

    public Sale() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Sale [createdAt=" + createdAt + ", goodsId=" + goodsId + ", id=" + id + ", number=" + number
                + ", price=" + price + ", unit=" + unit + "]";
    }

}
