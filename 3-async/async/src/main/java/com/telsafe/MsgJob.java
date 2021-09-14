package com.telsafe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author tangfh
 * @date 2021/9/14
 */
@Component
@Slf4j
public class MsgJob {
    @Async
    public void send(String msg) {
        log.info("开始发送");
        try {
            Thread.sleep(Duration.ofSeconds(10).toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("发送完成");
    }
}
