package com.wuyiccc.yuheng.event;

import lombok.Data;

/**
 * @author wuyiccc
 * @date 2023/10/10 23:30
 */
@Data
public class MsgEvent {

    public String id;

    public MsgEvent() {
    }

    public MsgEvent(String id) {
        this.id = id;
    }
}
