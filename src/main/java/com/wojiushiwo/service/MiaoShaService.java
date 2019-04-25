package com.wojiushiwo.service;

import com.wojiushiwo.dao.OrderDao;
import com.wojiushiwo.domain.model.MiaoShaUser;
import com.wojiushiwo.domain.model.MiaoshaOrder;
import com.wojiushiwo.domain.model.OrderInfo;
import com.wojiushiwo.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by 我就是我
 * 2019/4/25 下午5:36
 */
@Service
public class MiaoShaService {


    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderDao orderDao;

    @Transactional
    public OrderInfo generateOrderInfo(MiaoShaUser user, GoodsVo goods) {

        //减库存 规定只能秒杀一件
        goodsService.reduceGoodsStock(goods);

        //生成订单
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setDeliveryAddrId(null);
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setUserId(user.getId());
        orderInfo.setDeliveryAddrId(0L);
        //这里存的是 秒杀的价格
        orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);

        long orderId = orderDao.insert(orderInfo);

        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(orderId);
        miaoshaOrder.setUserId(user.getId());

        orderDao.insertMiaoshaOrder(miaoshaOrder);

        orderInfo.setId(orderId);
        return orderInfo;
    }

}
