package com.xftxyz.smms.dao;

import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.entity.Supplier;

public interface SupplierDao {
    // 新增供货商
    int addSupplier(Connection conn, Supplier supplier);

    // 根据供货商编号删除供货商
    boolean deleteSupplier(Connection conn, int supplierId);
    
    // 根据供货商编号更新供货商信息
    boolean updateSupplier(Connection conn, Supplier supplier);
    
    // 获取所有供货商
    List<Supplier> getAllSuppliers(Connection conn);

    // 检查供货商是否存在（根据名称）
    boolean checkSupplier(Connection conn, String supplierName);

}
