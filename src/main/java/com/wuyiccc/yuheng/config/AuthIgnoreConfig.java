package com.wuyiccc.yuheng.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuyiccc
 * @date 2023/7/17 13:13
 * 不需要权限拦截的url
 */
@ConfigurationProperties(prefix = "security.ignore")
@Configuration
public class AuthIgnoreConfig {

    private List<String> urls = new ArrayList<>();

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

}
