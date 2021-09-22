package com.telsafe;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * @author tangfh
 * @date 2021/9/14
 */
@ConfigurationProperties(prefix = "app")
public class AppCfg2 {
    List<String> urls;
    Map<String, Object> misc;
    Baidu baidu;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(final List<String> urls) {
        this.urls = urls;
    }

    public Map<String, Object> getMisc() {
        return misc;
    }

    public void setMisc(final Map<String, Object> misc) {
        this.misc = misc;
    }

    public Baidu getBaidu() {
        return baidu;
    }

    public void setBaidu(Baidu baidu) {
        this.baidu = baidu;
    }
}
