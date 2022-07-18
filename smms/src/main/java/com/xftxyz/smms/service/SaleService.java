package com.xftxyz.smms.service;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.dao.SaleDao;
import com.xftxyz.smms.dao.impl.SaleDaoImpl;
import com.xftxyz.smms.entity.Sale;
import com.xftxyz.smms.utils.FileUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SaleService {
    private Connection conn; // 数据库连接对象，通过构造方法初始化
    // DAO对象
    private SaleDao dao;
    // 观察列表
    private ObservableList<Sale> observableList;
    private Sale old;

    public SaleService(Connection conn) {
        this.conn = conn;
        dao = new SaleDaoImpl();
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
        List<Sale> sales = dao.getAllSales(conn);
        if (sales != null) {
            observableList.addAll(sales);
        }
    }

    public ObservableList<Sale> getObservableList() {
        return observableList;
    }

    // 添加销售记录
    public boolean addSale(Sale sale) {
        if (sale == null) {
            return false;
        }

        int index = dao.addSale(conn, sale);
        if (index < 0) {
            return false;
        }
        sale.setId(index);
        observableList.add(sale);

        ServiceFactory.getGoodsService().updateBy(sale);
        return true;
    }

    // 删除销售记录
    public boolean deleteSale(Sale sale) {
        if (sale == null) {
            return false;
        }

        boolean isSucc = dao.deleteSale(conn, sale.getId());
        if (!isSucc) {
            return false;
        }
        observableList.remove(sale);
        return true;
    }

    public Sale getUpdateCopy(Sale sale) {
        if (sale == null) {
            return null;
        }
        this.old = sale;
        return sale.copy();
    }

    // 更新销售记录
    public boolean updateSale(Sale newSale) {
        if (this.old == null) {
            return false;
        }

        if (newSale == null) {
            return false;
        }

        boolean isSucc = dao.updateSale(conn, newSale);
        if (!isSucc) {
            return false;
        }
        observableList.set(observableList.indexOf(this.old), newSale);
        this.old = null;
        return true;
    }

    // 导出
    public void export(File file) {
        FileUtil.writeExcel(file, Sale.class, observableList);
    }
}
