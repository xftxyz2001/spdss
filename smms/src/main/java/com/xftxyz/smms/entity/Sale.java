package com.xftxyz.smms.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.alibaba.excel.annotation.ExcelProperty;

public class Sale {
    @ExcelProperty("销售单号")
    private int id; // 销售单编号
    @ExcelProperty("商品名")
    private String goodsName; // 商品名
    @ExcelProperty("销售价")
    private BigDecimal price; // 销售价
    @ExcelProperty("销售数量")
    private BigDecimal num; // 销售数量
    @ExcelProperty("单位")
    private String unit; // 单位
    @ExcelProperty("销售时间")
    private Timestamp time; // 销售时间

    public Sale() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodName) {
        this.goodsName = goodName;
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
        Sale other = (Sale) obj;
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
        return "Sale [goodsName=" + goodsName + ", id=" + id + ", num=" + num + ", price=" + price + ", time=" + time
                + ", unit=" + unit + "]";
    }

    public Sale copy() {
        Sale sale = new Sale();
        sale.setId(this.id);
        sale.setGoodsName(this.goodsName);
        sale.setPrice(this.price);
        sale.setNum(this.num);
        sale.setUnit(this.unit);
        sale.setTime(this.time);
        return sale;
    }

}
