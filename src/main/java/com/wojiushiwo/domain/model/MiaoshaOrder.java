package com.wojiushiwo.domain.model;

import lombok.Data;

/**
 * Created by 我就是我
 * 2019/4/24 下午6:07
 * 秒杀订单表
 */
@Data
public class MiaoshaOrder {
    private Long id;
    private Long userId;
    private Long  orderId;
    private Long goodsId;

}
