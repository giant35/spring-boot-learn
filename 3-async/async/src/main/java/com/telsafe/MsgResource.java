package com.telsafe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author tangfh
 * @date 2021/9/14
 */
@RestController
@RequestMapping(path = "msg")
@Slf4j
public class MsgResource {
    @Autowired
    MsgJob msgJob;

    @GetMapping("send")
    @PostMapping("send")
    public String startSend() {
        log.info("startSend enter...");
        //msgJob.send("消息内容");
        send2("ssss");
        log.info("startSend return");
        return "" + LocalDateTime.now();
    }

    //not work//TODO: !!!
    @Async
    public void send2(String msg) {
        log.info("开始发送2");
        try {
            Thread.sleep(Duration.ofSeconds(30).toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("发送完成2");
    }

}
