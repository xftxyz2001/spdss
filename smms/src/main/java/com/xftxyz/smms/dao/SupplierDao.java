package com.xftxyz.smms.dao;

import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.entity.Supplier;

public interface SupplierDao {
    // 获取所有供货商
    List<Supplier> getAllSuppliers(Connection conn);

    // 根据供货商编号获取供货商
    Supplier getSupplierBySupplierId(Connection conn, int supplierId);

    // 根据供货商名称获取供货商
    Supplier getSupplierBySupplierName(Connection conn, String supplierName);

    // 根据地点获取供货商
    List<Supplier> getSupplierByAddress(Connection conn, String address);

    // 根据电话获取供货商
    Supplier getSupplierByPhone(Connection conn, String phone);

    // 检查供货商是否存在（根据名称）
    boolean isSupplierExist(Connection conn, String supplierName);

    // 新增供货商
    int addSupplier(Connection conn, Supplier supplier);

    // 根据供货商编号删除供货商
    boolean deleteSupplierBySupplierId(Connection conn, int supplierId);

    // 根据地点删除供货商
    int deleteSupplierByAddress(Connection conn, String address);

    // 修改供货商联系方式
    boolean updateSupplierPhoneBySupplierId(Connection conn, int supplierId, String phone);

    // 修改供货商地址
    boolean updateSupplierAddressBySupplierId(Connection conn, int supplierId, String address);

}
