package com.wojiushiwo.dao;

import com.wojiushiwo.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by 我就是我
 * 2019/4/24 下午10:58
 */
@Mapper
public interface GoodsDao {

    @Select("select goods.*,miaosha_goods.miaosha_price,miaosha_goods.stock_count,miaosha_goods.start_date,miaosha_goods.end_date from goods left join miaosha_goods on goods.id=miaosha_goods.goods_id")
    List<GoodsVo> list();

    @Select("select goods.*,miaosha_goods.miaosha_price,miaosha_goods.stock_count,miaosha_goods.start_date,miaosha_goods.end_date from goods left join miaosha_goods on goods.id=miaosha_goods.goods_id where goods.id=#{goodsId}")
    GoodsVo getGoodsById(@Param("goodsId") long goodsId);

    @Update("update miaosha_goods set stock_count=stock_count-1 where goods_id=#{goodsId} and stock_count>=1")
    int reduceGoodStock(@Param("goodsId")long goodsId);

}
