package com.telsafe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author tangfh
 * @date 2021/9/14
 */
@Component
public class AppCfg1 {
    @Value("${app.key}")
    String key;
    @Value("${app.urls[0]}")
    String url0;
    //NOT WORK @Value("${app.urls}")
    //NOT WORK List<String> urls;
    //NOT WORK @Value("${app.misc}")
    //NOT WORK Map<String, Object> misc;

//    public List<String> getUrls() {
//        return urls;
//    }
//
//    public Map<String, Object> getMisc() {
//        return misc;
//    }

    public String getKey() {
        return key;
    }

    public String getUrl0() {
        return url0;
    }
}
