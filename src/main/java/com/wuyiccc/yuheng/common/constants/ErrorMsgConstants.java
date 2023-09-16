package com.wuyiccc.yuheng.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author wuyiccc
 * @date 2023/9/15 21:08
 * 错误提示信息常量
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMsgConstants {

    public static final String DATA_REPEAT = "数据重复";
    public static final String USERNAME_OR_PASSWORD_NOT_RIGHT = "用户名或密码不正确";
    public static final String USER_NOT_FOUND = "用户不存在";

    public static final String DATA_NOT_FOUND = "数据不存在";
    public static final String FILE_UPLOAD_FAILED = "文件上传失败";
}
