package com.telsafe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangfh
 * @date 2021/9/14
 */
@RestController
public class HelloResource {
    @GetMapping("/hello")
    public UniResp<String> hello(@RequestParam(name = "name") String aname) {
        return new UniResp(1, null, "你好" + aname);
    }

    @GetMapping("/hello2")
    public String hello2(@RequestParam(name = "name") String aname) {
        return "你好" + aname;
    }

    @GetMapping("/age")
    public int age() {
        return 11;
    }
}
