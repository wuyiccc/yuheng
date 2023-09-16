package com.wuyiccc.yuheng.common.enums;

/**
 * @author wuyiccc
 * @date 2023/6/19 22:59
 */
public enum ResponseStatusEnum {

    SUCCESS(200, true, "操作成功！"),
    FAILED(500, false, "操作失败！"),

    TOKEN_FAILED(501, false, "token失效!")
    ;





    // 响应业务状态
    private final Integer status;
    // 调用是否成功
    private final Boolean success;
    // 响应消息，可以为成功或者失败的消息
    private final String msg;

    ResponseStatusEnum(Integer status, Boolean success, String msg) {
        this.status = status;
        this.success = success;
        this.msg = msg;
    }

    public Integer status() {
        return status;
    }
    public Boolean success() {
        return success;
    }
    public String msg() {
        return msg;
    }
}
