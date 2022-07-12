package com.xftxyz.smms.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Purchase {
    private int id;
    private int supplierId;
    private int goodsId;
    private BigDecimal price;
    private BigDecimal number;
    private String unit;
    // private String remark;
    private Timestamp createdAt;

    public Purchase() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
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
        return "Purchase [createdAt=" + createdAt + ", goodsId=" + goodsId + ", id=" + id + ", number=" + number
                + ", price=" + price + ", supplierId=" + supplierId + ", unit=" + unit + "]";
    }

}
