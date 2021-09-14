package com.telsafe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author tangfh
 * @date 2021/9/14
 */
@RestController
@RequestMapping("diag")
public class DiagResource {
    @Value("${server.port}")
    private Integer port;

    @GetMapping
    public Map<String, Object> sys() {
        return Map.of("port", port);
    }
}
