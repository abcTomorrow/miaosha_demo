package com.wojiushiwo.controller;

import com.wojiushiwo.redis.RedisService;
import com.wojiushiwo.redis.UserKey;
import com.wojiushiwo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 我就是我
 * 2019/4/21 下午12:29
 */
@Controller
@RequestMapping("/demo")
public class SampleController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        String name = userService.getNameById(1);
        redisService.set(UserKey.getByName, "name", name);
        model.addAttribute("name", name);
        return "hello";

    }

}
