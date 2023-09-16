package com.wuyiccc.yuheng.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wuyiccc
 * @date 2023/9/16 06:40
 */
@Getter
@AllArgsConstructor
public enum ContentTypeEnum {

    IMG("image/jpeg", "图片"),
    ;


    private final String type;
    private final String desc;
}
