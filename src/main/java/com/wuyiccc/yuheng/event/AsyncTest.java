package com.wuyiccc.yuheng.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author wuyiccc
 * @date 2023/10/10 23:53
 */
@Component
@Slf4j
public class AsyncTest {

    @Async
    public void sendMsg(MsgEvent event) {

        log.info("event: {}", event.getId());
    }
}
