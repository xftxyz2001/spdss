package com.xftxyz.smms.entity;

import java.math.BigDecimal;

public class Goods {
    private int id;
    private String name;
    private BigDecimal price;
    private String description;
    private String category;
    private BigDecimal number;
    private String unit;
    private boolean onSell; // 在售状态，false为下架，true为在售

    public Goods() {
    }

    public boolean isOnSell() {
        return onSell;
    }

    public void setOnSell(boolean onSell) {
        this.onSell = onSell;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    @Override
    public String toString() {
        return "Goods [category=" + category + ", description=" + description + ", id=" + id + ", name=" + name
                + ", number=" + number + ", onSell=" + onSell + ", price=" + price + ", unit=" + unit + "]";
    }

}