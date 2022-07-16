package com.xftxyz.smms.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.dao.BaseDao;
import com.xftxyz.smms.dao.GoodsDao;
import com.xftxyz.smms.entity.Goods;

public class GoodsDaoImpl extends BaseDao<Goods> implements GoodsDao {

    @Override
    public int addGoods(Connection conn, Goods goods) {
        String sql = "insert into goods(name, describe, price, num, unit) values(?, ?, ?, ?, ?)";
        return insert(conn, sql, goods.getName(), goods.getDescribe(), goods.getPrice(), goods.getNum(),
                goods.getUnit());
    }

    @Override
    public boolean deleteGoodsByGoodsId(Connection conn, int goodsId) {
        String sql = "delete from goods where id = ?";
        return update(conn, sql, goodsId) > 0;
    }

    @Override
    public boolean updateGoods(Connection conn, Goods goods) {
        String sql = "update goods set name = ?, describe = ?, price = ?, num = ?, unit = ? where id = ?";
        return update(conn, sql, goods.getName(), goods.getDescribe(), goods.getPrice(), goods.getNum(),
                goods.getUnit(), goods.getId()) > 0;
    }

    @Override
    public List<Goods> getAllGoods(Connection conn) {
        String sql = "select * from goods";
        return getBeanList(conn, sql);
    }

    @Override
    public boolean checkGoods(Connection conn, String goodsName) {
        String sql = "select count(*) from goods where name = ?";
        return (int) getValue(conn, sql, goodsName) > 0;
    }

}
