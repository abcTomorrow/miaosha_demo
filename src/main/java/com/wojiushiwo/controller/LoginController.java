package com.wojiushiwo.controller;

import com.wojiushiwo.result.CodeMsg;
import com.wojiushiwo.result.Result;
import com.wojiushiwo.service.MiaoShaUserService;
import com.wojiushiwo.util.ValidatorUtil;
import com.wojiushiwo.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by 我就是我
 * 2019/4/21 下午7:58
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private MiaoShaUserService miaoShaUserService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(@Valid LoginVo loginVo, HttpServletResponse response) {
        if (loginVo == null) {
            return Result.error(CodeMsg.SERVER_ERROR);
        }

        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        if (StringUtils.isEmpty(mobile)) {
            return Result.error(CodeMsg.MOBILE_EMPTY);
        }
        if (StringUtils.isEmpty(password)) {
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }
        if (!ValidatorUtil.isMobile(mobile)) {
            return Result.error(CodeMsg.MOBILE_ERROR);
        }
        CodeMsg codeMsg = miaoShaUserService.login(loginVo, response);
        if (codeMsg.getCode() != 0) {
            return Result.error(codeMsg);
        } else {
            return Result.success(true);
        }
    }

}
