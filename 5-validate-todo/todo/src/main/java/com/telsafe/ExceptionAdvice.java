package com.telsafe;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Map;

/**
 * @author tangfh
 * @date 2021/9/15
 */
@RestControllerAdvice(basePackages = "com.telsafe")
public class ExceptionAdvice {
    @ExceptionHandler(ConstraintViolationException.class)
    public Map onErr(ConstraintViolationException e) {
        return Map.of("msg", "输入有误", "ex", e.getMessage());
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public Map onErr2(org.springframework.web.bind.MethodArgumentNotValidException e) {
        return Map.of("msg", "参数有误", "ex", e.getMessage());
    }

}
