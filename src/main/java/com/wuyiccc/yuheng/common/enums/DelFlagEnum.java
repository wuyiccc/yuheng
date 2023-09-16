package com.wuyiccc.yuheng.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wuyiccc
 * @date 2023/9/15 20:54
 */
@Getter
@AllArgsConstructor
public enum DelFlagEnum {

    /**
     * 未删除
     */
    NO_DELETED(0, "未删除"),

    /**
     * 删除
     */
    DELETED(1, "删除"),
    ;


    private final Integer type;
    private final String desc;
}
