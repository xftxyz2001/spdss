package com.xftxyz.smms.dao;

import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.entity.Goods;

public interface GoodsDao {
    // 新增商品
    int addGoods(Connection conn, Goods goods);
    
    // 删除商品
    boolean deleteGoodsByGoodsId(Connection conn, int goodsId);

    // 更新商品
    boolean updateGoods(Connection conn, Goods goods);

    // 获取所有商品
    List<Goods> getAllGoods(Connection conn);

    // 检查商品存在
    boolean checkGoods(Connection conn, String goodsName);

}
