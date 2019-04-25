package com.wojiushiwo.controller;

import com.wojiushiwo.domain.model.MiaoShaUser;
import com.wojiushiwo.service.GoodsService;
import com.wojiushiwo.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

/**
 * Created by 我就是我
 * 2019/4/24 上午10:30
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/to_list")
    public String list(Model model, MiaoShaUser user) {
        //这里user为空的含义是 用户未登录，未登录的话 跳转到登录页面
        if (Objects.isNull(user)) {
            return "login";
        }
        model.addAttribute("user", user);
        //查询商品列表
        List<GoodsVo> goodsList = goodsService.list();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

    @GetMapping("/to_detail/{goodsId}")
    public String detail(@PathVariable("goodsId") long goodsId, MiaoShaUser miaoShaUser, Model model) {

        model.addAttribute("user", miaoShaUser);

        GoodsVo goods = goodsService.getGoodsVoById(goodsId);
        model.addAttribute("goods", goods);

        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();
        //秒杀状态
        int miaoshaStatus = 0;
        //剩余时间
        int remainSeconds = 0;
        //秒杀已经结束
        if (now > endAt) {
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else if (now < startAt) {
            //秒杀还没开始
            miaoshaStatus = 0;
            remainSeconds = (int) ((startAt - now) / 1000);
        } else {
            //秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }

}
