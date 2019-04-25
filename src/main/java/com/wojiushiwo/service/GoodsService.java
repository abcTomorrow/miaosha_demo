package com.wojiushiwo.service;

import com.wojiushiwo.dao.GoodsDao;
import com.wojiushiwo.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 我就是我
 * 2019/4/24 下午10:56
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;


    public List<GoodsVo> list() {
        return goodsDao.list();
    }

    public GoodsVo getGoodsVoById(long goodsId) {
        return goodsDao.getGoodsById(goodsId);
    }

    public void reduceGoodsStock(GoodsVo goods){
        goodsDao.reduceGoodStock(goods.getId());
    }
}
