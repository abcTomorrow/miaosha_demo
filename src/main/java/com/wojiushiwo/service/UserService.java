package com.wojiushiwo.service;

import com.wojiushiwo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 我就是我
 * 2019/4/21 下午1:35
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public String getNameById(Integer id) {
        return userDao.getNameById(id);
    }

}
