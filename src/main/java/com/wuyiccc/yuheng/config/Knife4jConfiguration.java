package com.wuyiccc.yuheng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author wuyiccc
 * @date 2023/6/26 21:59
 * doc文档配置
 */

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        //.title("swagger-bootstrap-ui-demo RESTful APIs")
                        .description("玉衡开发平台")
                        .termsOfServiceUrl("http://www.xx.com/")
                        .contact("xxx@qq.com")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("玉衡开发平台")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.wuyiccc.yuheng.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}