package com.xftxyz.smms.dao;

import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.entity.Sale;

public interface SaleDao {
    // 新增销售记录
    int addSale(Connection conn, Sale sale);

    // 根据编号删除销售记录
    boolean deleteSale(Connection conn, int id);

    // 根据编号修改其他信息
    boolean updateSale(Connection conn, Sale sale);

    // 查询所有销售记录
    List<Sale> getAllSales(Connection conn);

}
