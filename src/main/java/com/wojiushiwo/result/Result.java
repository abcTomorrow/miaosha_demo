package com.wojiushiwo.result;

import lombok.Getter;

import java.util.Objects;

/**
 * Created by 我就是我
 *
 * @author 我就是我
 * 2019/4/21 下午12:44
 */
@Getter
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private Result(CodeMsg cm) {
        if (Objects.isNull(cm)) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> error(CodeMsg cm) {
        return new Result<>(cm);
    }
}
