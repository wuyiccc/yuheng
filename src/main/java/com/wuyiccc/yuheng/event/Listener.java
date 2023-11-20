package com.wuyiccc.yuheng.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wuyiccc
 * @date 2023/10/10 23:30
 */
@Slf4j
@Component
public class Listener {

    @Resource
    private AsyncTest asyncTest;

    @Async
    @EventListener(MsgEvent.class)
    public void sendMsg(MsgEvent event) {

        asyncTest.sendMsg(event);
        log.info("xxx");
    }


}
