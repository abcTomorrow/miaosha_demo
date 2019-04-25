package com.wojiushiwo.dao;

import com.wojiushiwo.domain.model.MiaoshaOrder;
import com.wojiushiwo.domain.model.OrderInfo;
import org.apache.ibatis.annotations.*;

/**
 * Created by 我就是我
 * 2019/4/25 下午5:31
 */
@Mapper
public interface OrderDao {

    @Select("select * from miaosha_order where user_id=#{userId} and goods_id=#{goodsId}")
    MiaoshaOrder getOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    @SelectKey(statement = "select last_insert_id()", keyColumn = "id", keyProperty = "id", resultType = long.class, before = false)
    long insert(OrderInfo orderInfo);

    @Insert("insert into miaosha_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
    int insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);

}
