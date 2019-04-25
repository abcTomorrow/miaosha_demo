package com.wojiushiwo.domain.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by 我就是我
 * 2019/4/24 下午6:07
 * 秒杀商品表
 */
@Data
public class MiaoshaGoods {

    private Long id;
    private Long goodsId;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
