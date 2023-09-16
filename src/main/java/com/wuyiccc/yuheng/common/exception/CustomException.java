package com.wuyiccc.yuheng.common.exception;


import com.wuyiccc.yuheng.common.enums.ResponseStatusEnum;

/**
 * @author wuyiccc
 * @date 2023/6/26 21:54
 */
public class CustomException extends RuntimeException {

    private ResponseStatusEnum responseStatusEnum;

    private String msg;

    public CustomException(ResponseStatusEnum responseStatusEnum) {
        super("异常状态码为: " + responseStatusEnum.status() + "异常信息为: " + responseStatusEnum.msg());
        this.responseStatusEnum = responseStatusEnum;
    }

    public CustomException(String errorMsg) {
        super(errorMsg);
        this.responseStatusEnum = ResponseStatusEnum.FAILED;
        this.msg = errorMsg;
    }

    public ResponseStatusEnum getResponseStatusEnum() {
        return responseStatusEnum;
    }

    public void setResponseStatusEnum(ResponseStatusEnum responseStatusEnum) {
        this.responseStatusEnum = responseStatusEnum;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
