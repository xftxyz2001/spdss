package com.xftxyz.smms.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.alibaba.excel.annotation.ExcelProperty;

public class Purchase {
    @ExcelProperty("采购单号")
    private int id; // 采购单编号
    @ExcelProperty("供应商名")
    private String supplierName; // 供应商名称
    @ExcelProperty("商品名")
    private String goodsName; // 商品名称
    @ExcelProperty("采购价")
    private BigDecimal price; // 采购价
    @ExcelProperty("采购数量")
    private BigDecimal num; // 采购数量
    @ExcelProperty("单位")
    private String unit; // 单位
    @ExcelProperty("采购时间")
    private Timestamp time; // 采购时间

    public Purchase() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((goodsName == null) ? 0 : goodsName.hashCode());
        result = prime * result + id;
        result = prime * result + ((num == null) ? 0 : num.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((supplierName == null) ? 0 : supplierName.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
        Purchase other = (Purchase) obj;
        if (goodsName == null) {
            if (other.goodsName != null)
                return false;
        } else if (!goodsName.equals(other.goodsName))
            return false;
        if (id != other.id)
            return false;
        if (num == null) {
            if (other.num != null)
                return false;
        } else if (!num.equals(other.num))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (supplierName == null) {
            if (other.supplierName != null)
                return false;
        } else if (!supplierName.equals(other.supplierName))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        if (unit == null) {
            if (other.unit != null)
                return false;
        } else if (!unit.equals(other.unit))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Purchase [goodsName=" + goodsName + ", id=" + id + ", num=" + num + ", price=" + price
                + ", supplierName=" + supplierName + ", time=" + time + ", unit=" + unit + "]";
    }

    public Purchase copy() {
        Purchase purchase = new Purchase();
        purchase.setId(id);
        purchase.setSupplierName(supplierName);
        purchase.setGoodsName(goodsName);
        purchase.setPrice(price);
        purchase.setNum(num);
        purchase.setUnit(unit);
        purchase.setTime(time);
        return purchase;
    }

}
