package com.wojiushiwo.controller;

import com.wojiushiwo.domain.model.MiaoShaUser;
import com.wojiushiwo.domain.model.MiaoshaOrder;
import com.wojiushiwo.domain.model.OrderInfo;
import com.wojiushiwo.service.GoodsService;
import com.wojiushiwo.service.MiaoShaService;
import com.wojiushiwo.service.OrderService;
import com.wojiushiwo.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

/**
 * Created by 我就是我
 * 2019/4/25 下午5:24
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoShaController {

    @Autowired
    private GoodsService goodsService;


    @Autowired
    private OrderService orderService;


    @Autowired
    private MiaoShaService miaoShaService;

    @PostMapping("/do_miaosha")
    public String miaosha(Model model, MiaoShaUser miaoShaUser, @RequestParam("goodsId") long goodsId) {
        if (miaoShaUser == null) {
            return "login";
        }
        model.addAttribute("user", miaoShaUser);

        //判断待秒杀商品的库存是否足够
        GoodsVo goods = goodsService.getGoodsVoById(goodsId);
        if (goods.getStockCount() <= 0) {
            model.addAttribute("errMsg", "商品已经秒杀完毕");
            return "miaosha_fail";
        }

        //从秒杀表中查询 判断该用户是否已经秒杀过
        MiaoshaOrder miaoshaOrder = orderService.getOrderByUserIdGoodsId(goodsId, miaoShaUser.getId());
        if(Objects.nonNull(miaoshaOrder)){
            model.addAttribute("errMsg", "抱歉,不能重复秒杀");
            return "miaosha_fail";
        }

        //开始秒杀 减掉商品库存 生成订单 生成秒杀订单
        OrderInfo orderInfo = miaoShaService.generateOrderInfo(miaoShaUser, goods);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";

    }

}
