package com.wojiushiwo.vo;

import com.wojiushiwo.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by 我就是我
 * 2019/4/21 下午8:11
 */
@Data
public class LoginVo {

    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;

}
