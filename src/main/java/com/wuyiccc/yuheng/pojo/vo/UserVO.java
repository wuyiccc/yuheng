package com.wuyiccc.yuheng.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wuyiccc
 * @date 2023/9/15 22:24
 */
@ApiModel(value = "用户信息显示实体")
@Data
public class UserVO {

    @ApiModelProperty("用户id")
    private String id;

    /**
     * 登录名
     */
    @ApiModelProperty("用户名")
    private String username;


    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickname;

    /**
     * 备注
     */
    @ApiModelProperty("用户信息备注")
    private String remark;

    /**
     * 头像
     */
    @ApiModelProperty("用户头像url")
    private String faceUrl;
}
