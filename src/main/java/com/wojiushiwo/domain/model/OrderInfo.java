package com.wojiushiwo.domain.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by 我就是我
 * 2019/4/24 下午6:08
 * 订单信息表
 */
@Data
public class OrderInfo {

    private Long id;
    private Long userId;
    private Long goodsId;
    private Long deliveryAddrId;
    private String goodsName;
    private Integer goodsCount;
    private Double goodsPrice;
    private Integer orderChannel;
    private Integer status;
    private Date createDate;
    private Date payDate;

}
