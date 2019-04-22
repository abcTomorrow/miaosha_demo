package com.wojiushiwo.domain.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by 我就是我
 * 2019/4/21 下午8:22
 */
@Data
public class MiaoShaUser {
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
