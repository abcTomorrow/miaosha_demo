package com.wojiushiwo.service;

import com.wojiushiwo.dao.MiaoShaUserDao;
import com.wojiushiwo.domain.model.MiaoShaUser;
import com.wojiushiwo.result.CodeMsg;
import com.wojiushiwo.util.MD5Util;
import com.wojiushiwo.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by 我就是我
 * 2019/4/21 下午8:31
 *
 * @author wojiushiwo
 */
@Service
public class MiaoShaUserService {

    @Autowired
    private MiaoShaUserDao miaoShaUserDao;

    public MiaoShaUser getById(long id) {
        return miaoShaUserDao.getById(id);
    }

    public CodeMsg login(LoginVo loginVo) {

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
        return CodeMsg.SUCCESS;
    }
}
