package com.xftxyz.smms.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import com.xftxyz.smms.dao.BaseDao;
import com.xftxyz.smms.dao.SaleDao;
import com.xftxyz.smms.entity.Sale;

public class SaleDaoImpl extends BaseDao<Sale> implements SaleDao {

    @Override
    public boolean addSale(Connection conn, Sale sale) {
        /*
         * private int goodsId; // 商品编号
         * private BigDecimal price; // 销售价
         * private BigDecimal number; // 销售数量
         * private String unit; // 单位
         * private Timestamp createdAt; // 销售时间
         */
        String sql = "insert into sale(goodsId, price, number, unit, createdAt) values(?, ?, ?, ?, ?)";
        return update(conn, sql, sale.getGoodsId(), sale.getPrice(), sale.getNumber(), sale.getUnit(),
                sale.getCreatedAt()) > 0;
    }

    @Override
    public List<Sale> getSaleByGoodsId(Connection conn, int goodsId) {
        String sql = "select * from sale where goodsId = ?";
        return getBeanList(conn, sql, goodsId);
    }

    @Override
    public List<Sale> getSaleByTimeRange(Connection conn, Timestamp minTime, Timestamp maxTime) {
        String sql = "select * from sale where createdAt between ? and ?";
        return getBeanList(conn, sql, minTime, maxTime);
    }

    @Override
    public List<Sale> getSaleByPriceRange(Connection conn, BigDecimal minPrice, BigDecimal maxPrice) {
        String sql = "select * from sale where price between ? and ?";
        return getBeanList(conn, sql, minPrice, maxPrice);
    }

    @Override
    public boolean updateSale(Connection conn, Sale sale) {
        String sql = "update sale set goodsId = ?, price = ?, number = ?, unit = ?, createdAt = ? where id = ?";
        return update(conn, sql, sale.getGoodsId(), sale.getPrice(), sale.getNumber(), sale.getUnit(),
                sale.getCreatedAt(), sale.getId()) > 0;
    }

    @Override
    public boolean deleteSale(Connection conn, int id) {
        String sql = "delete from sale where id = ?";
        return update(conn, sql, id) > 0;
    }

}