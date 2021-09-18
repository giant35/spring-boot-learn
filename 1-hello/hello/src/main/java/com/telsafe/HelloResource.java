package com.telsafe;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author tangfh
 * @date 2021/9/14
 */
@RestController
@RequestMapping(path = "/hello")//可选
public class HelloResource {
    @GetMapping("/{name}")
    public HelloVo sayHello(@PathVariable(name = "name") String aname) {
        return new HelloVo("你好o", aname);
    }

    @GetMapping("/{name}/sayHello2")
    //@ResponseBody
    public String sayHello2(@PathVariable(name = "name") String aname) {
        return "你好吗？" + aname;
    }

}
