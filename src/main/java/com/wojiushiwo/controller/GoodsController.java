package com.wojiushiwo.controller;

import com.wojiushiwo.domain.model.MiaoShaUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

/**
 * Created by 我就是我
 * 2019/4/24 上午10:30
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @RequestMapping("/to_list")
    public String list(Model model, MiaoShaUser user) {
        //这里user为空的含义是 用户未登录，未登录的话 跳转到登录页面
        if (Objects.isNull(user)) {
            return "login";
        }
        model.addAttribute("user", user);
        return "goods_list";
    }

}
