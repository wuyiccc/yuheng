package com.wuyiccc.yuheng.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wuyiccc
 * @date 2023/9/12 23:02
 * sa-token配置
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    @Autowired
    private AuthIgnoreConfig authIgnoreConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验, 不需要sa-token校验的接口必须加@SaIgnore注解忽略
        registry.addInterceptor(new SaInterceptor(handler -> StpUtil.checkLogin())).addPathPatterns("/**")
                .excludePathPatterns(authIgnoreConfig.getUrls());
    }

}
