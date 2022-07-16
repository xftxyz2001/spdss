package com.xftxyz.smms.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.dao.BaseDao;
import com.xftxyz.smms.dao.SaleDao;
import com.xftxyz.smms.entity.Sale;

public class SaleDaoImpl extends BaseDao<Sale> implements SaleDao {

    @Override
    public int addSale(Connection conn, Sale sale) {
        String sql = "insert into sales(goodsName, price, num, unit, time) values(?, ?, ?, ?, ?)";
        return insert(conn, sql, sale.getGoodsName(), sale.getPrice(), sale.getNum(), sale.getUnit(), sale.getTime());
    }

    @Override
    public boolean deleteSale(Connection conn, int id) {
        String sql = "delete from sales where id = ?";
        return update(conn, sql, id) > 0;
    }

    @Override
    public boolean updateSale(Connection conn, Sale sale) {
        String sql = "update sales set goodsName = ?, price = ?, num = ?, unit = ?, time = ? where id = ?";
        return update(conn, sql, sale.getGoodsName(), sale.getPrice(), sale.getNum(), sale.getUnit(), sale.getTime(),
                sale.getId()) > 0;
    }

    @Override
    public List<Sale> getAllSales(Connection conn) {
        String sql = "select * from sales";
        return getBeanList(conn, sql);
    }

}