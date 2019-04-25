package com.wojiushiwo.domain.model;

import lombok.Data;

/**
 * Created by 我就是我
 * 2019/4/24 下午6:06
 * 商品表
 */
@Data
public class Goods {
    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Double goodsPrice;
    private Integer goodsStock;
}
