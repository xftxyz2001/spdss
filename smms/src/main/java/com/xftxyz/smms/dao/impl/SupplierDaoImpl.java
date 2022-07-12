package com.xftxyz.smms.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.dao.BaseDao;
import com.xftxyz.smms.dao.SupplierDao;
import com.xftxyz.smms.entity.Supplier;

public class SupplierDaoImpl extends BaseDao<Supplier> implements SupplierDao {

    @Override
    public List<Supplier> getAllSuppliers(Connection conn) {
        String sql = "select * from supplier";
        return getBeanList(conn, sql);
    }

    @Override
    public Supplier getSupplierBySupplierId(Connection conn, int supplierId) {
        String sql = "select * from supplier where id = ?";
        return getBean(conn, sql, supplierId);
    }

    @Override
    public Supplier getSupplierBySupplierName(Connection conn, String supplierName) {
        String sql = "select * from supplier where name = ?";
        return getBean(conn, sql, supplierName);
    }

    @Override
    public List<Supplier> getSupplierByAddress(Connection conn, String address) {
        String sql = "select * from supplier where address = ?";
        return getBeanList(conn, sql, address);
    }

    @Override
    public Supplier getSupplierByPhone(Connection conn, String phone) {
        String sql = "select * from supplier where phone = ?";
        return getBean(conn, sql, phone);
    }

    @Override
    public boolean isSupplierExist(Connection conn, String supplierName) {
        String sql = "select count(*) from supplier where name = ?";
        return (int) getValue(conn, sql, supplierName) > 0;
    }

    @Override
    public boolean addSupplier(Connection conn, Supplier supplier) {
        String sql = "insert into supplier(name, address, phone) values(?, ?, ?)";
        return update(conn, sql, supplier.getName(), supplier.getAddress(), supplier.getPhone()) > 0;
    }

    @Override
    public boolean deleteSupplierBySupplierId(Connection conn, int supplierId) {
        String sql = "delete from supplier where id = ?";
        return update(conn, sql, supplierId) > 0;
    }

    @Override
    public int deleteSupplierByAddress(Connection conn, String address) {
        String sql = "delete from supplier where address = ?";
        return update(conn, sql, address);
    }

    @Override
    public boolean updateSupplierPhoneBySupplierId(Connection conn, int supplierId, String phone) {
        String sql = "update supplier set phone = ? where id = ?";
        return update(conn, sql, phone, supplierId) > 0;
    }

    @Override
    public boolean updateSupplierAddressBySupplierId(Connection conn, int supplierId, String address) {
        String sql = "update supplier set address = ? where id = ?";
        return update(conn, sql, address, supplierId) > 0;
    }

}
