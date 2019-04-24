package com.wojiushiwo.service;

import com.wojiushiwo.dao.MiaoShaUserDao;
import com.wojiushiwo.domain.model.MiaoShaUser;
import com.wojiushiwo.redis.RedisService;
import com.wojiushiwo.redis.UserKey;
import com.wojiushiwo.result.CodeMsg;
import com.wojiushiwo.util.MD5Util;
import com.wojiushiwo.util.UUIDUtil;
import com.wojiushiwo.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Created by 我就是我
 * 2019/4/21 下午8:31
 *
 * @author wojiushiwo
 */
@Service
public class MiaoShaUserService {

    public static final String COOKI_NAME_TOKEN = "token";

    @Autowired
    private MiaoShaUserDao miaoShaUserDao;

    @Autowired
    private RedisService redisService;

    public MiaoShaUser getById(long id) {
        return miaoShaUserDao.getById(id);
    }

    public CodeMsg login(LoginVo loginVo, HttpServletResponse response) {

        String password = loginVo.getPassword();
        String mobile = loginVo.getMobile();

        MiaoShaUser user = miaoShaUserDao.getById(Long.parseLong(mobile));
        if (user == null) {
            return CodeMsg.MOBILE_NOT_EXIST;
        }
        String dbPass = user.getPassword();
        String salt = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(password, salt);
        if (!Objects.equals(calcPass, dbPass)) {
            return CodeMsg.PASSWORD_ERROR;
        }
        //登录成功后 生成用户session 存放在缓存中 借以实现分布式session
        String token = UUIDUtil.generateUUID();
        addCookie(response, token, user);

        return CodeMsg.SUCCESS;
    }

    private void addCookie(HttpServletResponse response, String token, MiaoShaUser user) {
        //将token信息存放在redis中
        redisService.set(UserKey.getByToken, token, user);
        //生成cookie
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(60);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    //从缓存中根据token 获取登录用户的信息
    public MiaoShaUser getByToken(HttpServletResponse response, String token) {
        //如果token未过期 且存在 则这次操作 要延长cookie的有效期
        MiaoShaUser user = redisService.get(UserKey.getByToken, token, MiaoShaUser.class);
        if (user != null) {
            addCookie(response, token, user);
        }
        return user;
    }
}
