package com.xftxyz.smms.service;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.dao.GoodsDao;
import com.xftxyz.smms.dao.impl.GoodsDaoImpl;
import com.xftxyz.smms.entity.Goods;
import com.xftxyz.smms.entity.Purchase;
import com.xftxyz.smms.entity.Sale;
import com.xftxyz.smms.utils.FileUtil;

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
        if (index < 0) {
            return false;
        }
        goods.setId(index);
        observableList.add(goods);
        return true;
    }

    // 获取所有商品名
    public String[] getAllGoodsName() {
        String[] names = new String[observableList.size()];
        for (int i = 0; i < observableList.size(); i++) {
            names[i] = observableList.get(i).getName();
        }
        return names;
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
        // Debug.log("old: " + this.old.toString());
        // Debug.log("updateGoods: " + newGoods.toString());
        boolean isSucc = dao.updateGoods(conn, newGoods);
        // Debug.log("updateGoods: " + isSucc);
        if (!isSucc) {
            return false;
        }
        observableList.set(observableList.indexOf(this.old), newGoods);
        return true;
    }

    // 更新库存
    public void updateBy(Purchase purchase) {
        // for (int i = 0; i < observableList.size(); i++) {
        // Goods goods = observableList.get(i);
        // if (goods.getName().equals(purchase.getGoodsName()) &&
        // goods.getUnit().equals(purchase.getUnit())) {
        // goods.setNum(goods.getNum().add(purchase.getNum()));
        // observableList.set(i, goods);
        // }
        // }
    }

    public void updateBy(Sale sale) {
    }

    // 导出
    public void export(File file) {
        FileUtil.writeExcel(file, Goods.class, observableList);
    }

}
