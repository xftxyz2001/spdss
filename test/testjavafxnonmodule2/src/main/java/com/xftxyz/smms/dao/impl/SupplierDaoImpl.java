package com.xftxyz.smms.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.dao.BaseDao;
import com.xftxyz.smms.dao.SupplierDao;
import com.xftxyz.smms.entity.Supplier;

public class SupplierDaoImpl extends BaseDao<Supplier> implements SupplierDao {

    @Override
    public int addSupplier(Connection conn, Supplier supplier) {
        String sql = "insert into suppliers(name, tel, address) values(?, ?, ?)";
        return insert(conn, sql, supplier.getName(), supplier.getTel(), supplier.getAddress());
    }

    @Override
    public boolean deleteSupplier(Connection conn, int supplierId) {
        String sql = "delete from suppliers where id = ?";
        return update(conn, sql, supplierId) > 0;
    }

    @Override
    public boolean updateSupplier(Connection conn, Supplier supplier) {
        String sql = "update suppliers set name = ?, tel = ?, address = ? where id = ?";
        return update(conn, sql, supplier.getName(), supplier.getTel(), supplier.getAddress(), supplier.getId()) > 0;
    }

    @Override
    public List<Supplier> getAllSuppliers(Connection conn) {
        String sql = "select * from suppliers";
        return getBeanList(conn, sql);
    }

    @Override
    public boolean checkSupplier(Connection conn, String supplierName) {
        String sql = "select count(*) from suppliers where name = ?";
        return (Long) getValue(conn, sql, supplierName) > 0;
    }

}
