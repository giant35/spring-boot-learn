package com.telsafe;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @author tangfh
 * @date 2021/9/14
 */
@SpringBootApplication
@Configuration
@Slf4j //TODO
public class LogApp {
    final static Logger log2 = LoggerFactory.getLogger(LogApp.class);

    public static void main(String[] args) {
        log2.info("应用启动 {}", LocalDateTime.now());

        try {
            var s = args[100];
        } catch (Exception e) {
            log2.error("参数无效" + StringUtils.join(args, ""), e);
        }

        SpringApplication.run(LogApp.class, args);
    }

    @Bean
    public CommandLineRunner r() {
        return (argv) -> {
            //debug not work default //TODO
            log2.debug("aaa11 DEBUG  argv.length:{}", argv.length);
            log.info("aaa log argv.length:{}", argv.length);
        };
    }
}
