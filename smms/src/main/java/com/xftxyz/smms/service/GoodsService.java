package com.xftxyz.smms.service;

import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.dao.GoodsDao;
import com.xftxyz.smms.dao.impl.GoodsDaoImpl;
import com.xftxyz.smms.entity.Goods;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GoodsService {
    private Connection conn; // 数据库连接对象，通过构造方法初始化
    // DAO对象
    private GoodsDao dao;
    // 观察列表
    private ObservableList<Goods> observableList;
    private Goods old;

    public GoodsService(Connection conn) {
        this.conn = conn;
        dao = new GoodsDaoImpl();
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
        List<Goods> goodsList = dao.getAllGoods(conn);
        if (goodsList != null) {
            observableList.addAll(goodsList);
        }
    }

    public ObservableList<Goods> getObservableList() {
        return observableList;
    }

    // 添加商品
    public boolean addGoods(Goods goods) {
        if (goods == null) {
            return false;
        }

        if (dao.checkGoods(conn, goods.getName())) {
            return false; // 商品名已存在
        }
        int index = dao.addGoods(conn, goods);
        if (index < -1) {
            return false;
        }
        goods.setId(index);
        observableList.add(goods);
        return true;
    }

    // 删除商品
    public boolean deleteGoods(Goods goods) {
        if (goods == null) {
            return false;
        }
        boolean isSucc = dao.deleteGoodsByGoodsId(conn, goods.getId());
        if (!isSucc) {
            return false;
        }
        observableList.remove(goods);
        return true;
    }

    // 获取修改副本
    public Goods getUpdateCopy(Goods goods) {
        if (goods == null) {
            return null;
        }
        this.old = goods;
        return goods.copy();
    }

    // 更新商品
    public boolean updateGoods(Goods newGoods) {
        if (this.old == null) {
            return false;
        }
        if (newGoods == null) {
            return false;
        }
        boolean isSucc = dao.updateGoods(conn, newGoods);
        if (!isSucc) {
            return false;
        }
        observableList.set(observableList.indexOf(this.old), newGoods);
        return true;
    }

}
