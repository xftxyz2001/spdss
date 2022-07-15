package com.xftxyz.smms.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.dao.BaseDao;
import com.xftxyz.smms.dao.GoodsDao;
import com.xftxyz.smms.entity.Goods;

public class GoodsDaoImpl extends BaseDao<Goods> implements GoodsDao {

    @Override
    public List<Goods> getAllGoods(Connection conn) {
        String sql = "select * from goods";
        return getBeanList(conn, sql);
    }

    @Override
    public List<Goods> getOnSellGoods(Connection conn) {
        String sql = "select * from goods where onSell = 1";
        return getBeanList(conn, sql);
    }

    @Override
    public List<Goods> getOffShelvesGoods(Connection conn) {
        String sql = "select * from goods where onSell = 0";
        return getBeanList(conn, sql);
    }

    @Override
    public Goods getGoodsByGoodsId(Connection conn, int goodsId) {
        String sql = "select * from goods where id = ?";
        return getBean(conn, sql, goodsId);
    }

    @Override
    public Goods getGoodsByGoodsName(Connection conn, String goodsName) {
        String sql = "select * from goods where name = ?";
        return getBean(conn, sql, goodsName);
    }

    @Override
    public List<Goods> getGoodsByCategory(Connection conn, String category) {
        String sql = "select * from goods where category = ?";
        return getBeanList(conn, sql, category);
    }

    @Override
    public List<Goods> getGoodsByPriceRange(Connection conn, BigDecimal minPrice, BigDecimal maxPrice) {
        String sql = "select * from goods where price between ? and ?";
        return getBeanList(conn, sql, minPrice, maxPrice);
    }

    @Override
    public List<Goods> getGoodsByStockRange(Connection conn, BigDecimal minStock, BigDecimal maxStock) {
        String sql = "select * from goods where number between ? and ?";
        return getBeanList(conn, sql, minStock, maxStock);
    }

    @Override
    public boolean isGoodsExist(Connection conn, String goodsName) {
        String sql = "select count(*) from goods where name = ?";
        return (int) getValue(conn, sql, goodsName) > 0;
    }

    @Override
    public int addGoods(Connection conn, Goods goods) {
        /*
         * private String name;// 商品名称
         * private BigDecimal price;// 单价
         * private String description;// 描述
         * private String category;// 分类
         * private BigDecimal number;// 库存数量
         * private String unit; // 单位
         * private boolean onSell; // 在售状态，false为下架，true为在售
         */
        String sql = "insert into goods(name, price, description, category, number, unit, onSell) values(?, ?, ?, ?, ?, ?, ?)";
        return insert(conn, sql, goods.getName(), goods.getPrice(), goods.getDescription(), goods.getCategory(),
                goods.getNumber(), goods.getUnit(), goods.isOnSell());
    }

    @Override
    public boolean updateGoodsStockByGoodsId(Connection conn, int goodsId, BigDecimal stock) {
        String sql = "update goods set number = ? where id = ?";
        return update(conn, sql, stock, goodsId) > 0;
    }

    @Override
    public boolean updateGoodsPriceByGoodsId(Connection conn, int goodsId, BigDecimal price) {
        String sql = "update goods set price = ? where id = ?";
        return update(conn, sql, price, goodsId) > 0;
    }

    @Override
    public boolean updateGoodsDescribeByGoodsId(Connection conn, int goodsId, String describe) {
        String sql = "update goods set description = ? where id = ?";
        return update(conn, sql, describe, goodsId) > 0;
    }

    @Override
    public boolean offShelvesGoodsByGoodsId(Connection conn, int goodsId) {
        String sql = "update goods set onSell = 0 where id = ?";
        return update(conn, sql, goodsId) > 0;
    }

    @Override
    public boolean offShelvesGoodsByGoodsName(Connection conn, String goodsName) {
        String sql = "update goods set onSell = 0 where name = ?";
        return update(conn, sql, goodsName) > 0;
    }

    @Override
    public int offShelvesGoodsByCategory(Connection conn, String category) {
        String sql = "update goods set onSell = 0 where category = ?";
        return update(conn, sql, category);
    }

    @Override
    public boolean deleteGoodsByGoodsId(Connection conn, int goodsId) {
        String sql = "delete from goods where id = ?";
        return update(conn, sql, goodsId) > 0;
    }

    @Override
    public boolean onShelvesGoodsByGoodsId(Connection conn, int goodsId) {
        String sql = "update goods set onSell = 1 where id = ?";
        return update(conn, sql, goodsId) > 0;
    }

    @Override
    public boolean onShelvesGoodsByGoodsName(Connection conn, String goodsName) {
        String sql = "update goods set onSell = 1 where name = ?";
        return update(conn, sql, goodsName) > 0;
    }

    @Override
    public int onShelvesGoodsByCategory(Connection conn, String category) {
        String sql = "update goods set onSell = 1 where category = ?";
        return update(conn, sql, category);
    }

    @Override
    public int onSellAllGoods(Connection conn) {
        String sql = "update goods set onSell = 1";
        return update(conn, sql);
    }

    @Override
    public int offShelvesAllGoods(Connection conn) {
        String sql = "update goods set onSell = 0";
        return update(conn, sql);
    }

}
