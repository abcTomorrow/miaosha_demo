package com.wojiushiwo.vo;

import com.wojiushiwo.domain.model.Goods;
import lombok.Data;

import java.util.Date;

/**
 * Created by 我就是我
 * 2019/4/24 下午11:04
 */
@Data
public class GoodsVo extends Goods {
    private Double miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
