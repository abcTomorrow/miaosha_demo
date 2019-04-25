package com.wojiushiwo.service;

import com.wojiushiwo.dao.OrderDao;
import com.wojiushiwo.domain.model.MiaoshaOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 我就是我
 * 2019/4/25 下午5:31
 */
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    public MiaoshaOrder getOrderByUserIdGoodsId(long goodsId,long userId){
        return orderDao.getOrderByUserIdGoodsId(userId,goodsId);
    }

}
