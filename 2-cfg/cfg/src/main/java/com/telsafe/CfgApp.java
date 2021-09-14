package com.telsafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author tangfh
 * @date 2021/9/14
 */
@SpringBootApplication
@EnableConfigurationProperties(value = {AppCfg2.class})
public class CfgApp {
    public static void main(String[] args) {
        SpringApplication.run(CfgApp.class, args);
    }
}
