package com.xftxyz.smms.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.entity.Goods;

public interface GoodsDao {
    // 获取所有商品
    List<Goods> getAllGoods(Connection conn);

    // 获取在售商品
    List<Goods> getOnSellGoods(Connection conn);

    // 获取已下架商品
    List<Goods> getOffShelvesGoods(Connection conn);

    // 根据商品编号获取商品
    Goods getGoodsByGoodsId(Connection conn, int goodsId);

    // 根据商品名称获取商品
    Goods getGoodsByGoodsName(Connection conn, String goodsName);

    // 根据类别获取商品
    List<Goods> getGoodsByCategory(Connection conn, String category);

    // 根据价格范围获取商品
    List<Goods> getGoodsByPriceRange(Connection conn, BigDecimal minPrice, BigDecimal maxPrice);

    // 根据库存范围获取商品
    List<Goods> getGoodsByStockRange(Connection conn, BigDecimal minStock, BigDecimal maxStock);

    // 返回商品是否存在
    boolean isGoodsExist(Connection conn, String goodsName);

    // 新增商品
    boolean addGoods(Connection conn, Goods goods);

    // 根据商品编号修改商品库存
    boolean updateGoodsStockByGoodsId(Connection conn, int goodsId, BigDecimal stock);
    
    // 修改商品价格
    boolean updateGoodsPriceByGoodsId(Connection conn, int goodsId, BigDecimal price);

    // 修改商品描述
    boolean updateGoodsDescribeByGoodsId(Connection conn, int goodsId, String describe);

    // 根据商品编号下架商品
    boolean offShelvesGoodsByGoodsId(Connection conn, int goodsId);

    // 根据商品名称下架商品
    boolean offShelvesGoodsByGoodsName(Connection conn, String goodsName);

    // 根据商品种类下架商品
    int offShelvesGoodsByCategory(Connection conn, String category);

    // 根据商品编号上架商品
    boolean onShelvesGoodsByGoodsId(Connection conn, int goodsId);

    // 根据商品名称上架商品
    boolean onShelvesGoodsByGoodsName(Connection conn, String goodsName);

    // 根据商品种类上架商品
    int onShelvesGoodsByCategory(Connection conn, String category);
    
    // 删除商品
    boolean deleteGoodsByGoodsId(Connection conn, int goodsId);
}
