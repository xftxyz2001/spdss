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

    /**
     * 优化方向：保存过滤条件
     */
    private void initializeGoodsObservableList() {
        if (olGoods == null) {
            olGoods = FXCollections.observableArrayList();
        } else {
            olGoods.clear();
        }
        olGoods.addAll(gd.getAllGoods(conn));
    }

    public ObservableList<Goods> getGoodsObservableList() {
        return olGoods;
    }

    // 显示所有上架商品
    public void showOnSellGoods() {
        olGoods.clear();
        olGoods.addAll(gd.getOnSellGoods(conn));
    }

    // 显示所有下架商品
    public void showOffSellGoods() {
        olGoods.clear();
        olGoods.addAll(gd.getOffShelvesGoods(conn));
    }

    // 显示某种类别的商品
    public void showGoodsByCategory(String category) {
        olGoods.clear();
        olGoods.addAll(gd.getGoodsByCategory(conn, category));
    }

    // 上架所有商品，返回上架商品数
    public int onSellAllGoods() {
        int count = gd.onSellAllGoods(conn);
        initializeGoodsObservableList();
        return count;
    }

    // 下架所有商品，返回下架商品数
    public int offSellAllGoods() {
        int count = gd.offShelvesAllGoods(conn);
        initializeGoodsObservableList();
        return count;
    }

    // 删除商品
    public boolean deleteGoods(Goods goods) {
        if (goods == null) {
            return false;
        }
        boolean isSucc = gd.deleteGoodsByGoodsId(conn, goods.getId());
        if (!isSucc) {
            return false;
        }
        initializeGoodsObservableList();
        return true;
    }

    // 添加商品，商品存在则增加库存
    public boolean addGoods(Goods goods) {
        if (goods == null) {
            return false;
        }
        Goods g = gd.getGoodsByGoodsName(conn, goods.getName());
        // 商品存在
        if (g != null) {
            boolean isSucc = gd.updateGoodsStockByGoodsId(conn, g.getId(), g.getPrice().add(goods.getPrice()));
            if (!isSucc) {
                return false;
            }
            return true;
        }
        int index = gd.addGoods(conn, goods);
        if (index < -1) {
            return false;
        }
        initializeGoodsObservableList();
        return true;
    }

    // 修改商品描述
    public boolean updateGoodsDescription(Goods goods, String description) {
        if (goods == null) {
            return false;
        }
        if (description == null) {
            return false;
        }
        boolean isSucc = gd.updateGoodsDescribeByGoodsId(conn, goods.getId(), goods.getDescription());
        if (!isSucc) {
            return false;
        }
        initializeGoodsObservableList();
        return true;
    }

}
