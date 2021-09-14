package com.telsafe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangfh
 * @date 2021/9/14
 */
@RestController
@RequestMapping("/hello")
public class HelloResource {
    @GetMapping("/{name}")
    public HelloVo sayHello(@PathVariable(name = "name") String aname) {
        return new HelloVo("你好o", aname);
    }
}
