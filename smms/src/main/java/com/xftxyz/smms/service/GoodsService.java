package com.xftxyz.smms.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.xftxyz.smms.dao.GoodsDao;
import com.xftxyz.smms.dao.impl.GoodsDaoImpl;
import com.xftxyz.smms.entity.Goods;
import com.xftxyz.smms.utils.JDBCUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GoodsService {

    private Connection conn; // 数据库连接对象，通过构造方法初始化
    // DAO对象
    private GoodsDao gd;
    // 观察列表
    private ObservableList<Goods> olGoods;

    public GoodsService() throws ClassNotFoundException, SQLException, IOException {
        this.conn = JDBCUtil.getConnection();
        gd = new GoodsDaoImpl();
        olGoods = FXCollections.observableArrayList();
    }

    public GoodsService(Connection conn) {
        this.conn = conn;
        gd = new GoodsDaoImpl();
        initializeGoodsObservableList();
    }

    private void initializeGoodsObservableList() {
        olGoods = FXCollections.observableArrayList();
        olGoods.addAll(gd.getAllGoods(conn));
    }

    public ObservableList<Goods> getGoodsObservableList() {
        return olGoods;
    }

}
