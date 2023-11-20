package com.wuyiccc.yuheng.controller;

import com.wuyiccc.yuheng.event.AsyncTest;
import com.wuyiccc.yuheng.event.MsgEvent;
import com.wuyiccc.yuheng.pojo.vo.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author wuyiccc
 * @date 2023/9/12 23:21
 */
@Api(tags = "用户管理")
@Slf4j
@RestController
@RequestMapping("/hello")
@Validated
public class HelloController {

    @Resource
    private ApplicationContext applicationContext;

    @Autowired
    private AsyncTest asyncTest;

    @ApiOperation(value = "参数")
    @PostMapping("/test")
    public CommonResult<String> test(@RequestBody String body) throws InterruptedException {

        applicationContext.publishEvent(new MsgEvent("5"));

//        asyncTest.sendMsg(new MsgEvent("5"));

        log.info("xxx");
        return CommonResult.ok();
    }

}
