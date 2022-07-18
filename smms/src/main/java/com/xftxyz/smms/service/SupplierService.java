package com.xftxyz.smms.service;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.dao.SupplierDao;
import com.xftxyz.smms.dao.impl.SupplierDaoImpl;
import com.xftxyz.smms.entity.Supplier;
import com.xftxyz.smms.utils.FileUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SupplierService {
    private Connection conn; // 数据库连接对象，通过构造方法初始化
    // DAO对象
    private SupplierDao dao;
    // 观察列表
    private ObservableList<Supplier> observableList;
    private Supplier old;

    public SupplierService(Connection conn) {
        this.conn = conn;
        dao = new SupplierDaoImpl();
        initialize();
    }

    /**
     * 优化方向：保存过滤条件
     */
    private void initialize() {
        if (observableList == null) {
            observableList = FXCollections.observableArrayList();
        } else {
            observableList.clear();
        }
        List<Supplier> suppliers = dao.getAllSuppliers(conn);
        if (suppliers != null) {
            observableList.addAll(suppliers);
        }
    }

    public ObservableList<Supplier> getObservableList() {
        return observableList;
    }

    // 添加供应商
    public boolean addSupplier(Supplier supplier) {
        if (supplier == null) {
            return false;
        }

        if (dao.checkSupplier(conn, supplier.getName())) {
            return false; // 供应商名已存在
        }
        int index = dao.addSupplier(conn, supplier);
        if (index < 0) {
            return false;
        }
        supplier.setId(index);
        observableList.add(supplier);
        return true;
    }

    // 获取所有供应商名
    public String[] getAllSupplierName() {
        String[] supplierNames = new String[observableList.size()];
        for (int i = 0; i < observableList.size(); i++) {
            supplierNames[i] = observableList.get(i).getName();
        }
        return supplierNames;
    }

    // 删除供应商
    public boolean deleteSupplier(Supplier supplier) {
        if (supplier == null) {
            return false;
        }
        boolean isSucc = dao.deleteSupplier(conn, supplier.getId());
        if (!isSucc) {
            return false;
        }
        observableList.remove(supplier);
        return true;
    }

    public Supplier getUpdateCopy(Supplier supplier) {
        if (supplier == null) {
            return null;
        }
        this.old = supplier;
        return supplier.copy();
    }

    // 更新供应商
    public boolean updateSupplier(Supplier newSupplier) {
        if (this.old == null) {
            return false;
        }
        if (newSupplier == null) {
            return false;
        }
        boolean isSucc = dao.updateSupplier(conn, newSupplier);
        if (!isSucc) {
            return false;
        }
        observableList.set(observableList.indexOf(this.old), newSupplier);
        this.old = null;
        return true;
    }

    // 导出
    public void export(File file) {
        FileUtil.writeExcel(file, Supplier.class, observableList);
    }
}
