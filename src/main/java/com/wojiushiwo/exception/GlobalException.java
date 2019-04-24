package com.wojiushiwo.exception;

import com.wojiushiwo.result.CodeMsg;
import lombok.Getter;

/**
 * Created by 我就是我
 * 2019/4/22 下午2:55
 */
@Getter
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }


}
