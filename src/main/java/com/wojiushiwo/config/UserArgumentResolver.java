package com.wojiushiwo.config;

import com.wojiushiwo.domain.model.MiaoShaUser;
import com.wojiushiwo.service.MiaoShaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 我就是我
 * 2019/4/24 上午10:34
 * 为MiaoShaUser实现自定义参数解析器
 */
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private MiaoShaUserService miaoShaUserService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz = parameter.getParameterType();
        return clazz == MiaoShaUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //因为查看商品详情、秒杀等 都是基于登录过后的操作 因此在实现解析器时 也会判断用户是否登录 以及重新设置登录过后的cookie过期时间

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        //用户的token是在第一次登录成功后 保存在浏览器上 之后再登录会带过来
        String token = getToken(request, "token");
        if (StringUtils.isEmpty(token)) {
            //如果token为空 在controller上控制其跳转到登录页面
            return null;
        }
        return miaoShaUserService.getByToken(response, token);
    }

    private String getToken(HttpServletRequest request,String token){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(token)){
               return cookie.getValue();
            }
        }
        return null;
    }

}
