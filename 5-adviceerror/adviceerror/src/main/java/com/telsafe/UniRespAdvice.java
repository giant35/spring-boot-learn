package com.telsafe;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author tangfh
 * @date 2021/9/15
 */
@RestControllerAdvice(basePackages = "com.telsafe")
//ok @RestControllerAdvice(basePackages = {"com.telsafe","a.b",...})
public class UniRespAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(final MethodParameter returnType, final Class converterType) {
        final Class<?> retCls = returnType.getParameterType();
        if (retCls.equals(UniResp.class) || retCls.equals(String.class)) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(final Object body, final MethodParameter returnType, final MediaType selectedContentType, final Class selectedConverterType, final ServerHttpRequest request, final ServerHttpResponse response) {
        return new UniResp(1, null, body);
    }
}
