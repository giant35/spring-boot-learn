package com.telsafe;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author tangfh
 * @date 2021/9/15
 */
@RestControllerAdvice(basePackages = "com.telsafe")
public class ErrorAdvice {
    @ExceptionHandler(TsException.class)
    public UniResp onError(TsException e) {
        return new UniResp(-2, "出错", null);
    }

    @ExceptionHandler(Exception.class)
    public UniResp onError2(Exception e) {
        return new UniResp(-1, "未知错误", null);
    }
}
