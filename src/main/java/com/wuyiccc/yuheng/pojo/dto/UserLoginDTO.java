package com.wuyiccc.yuheng.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author wuyiccc
 * @date 2023/9/15 21:13
 */
@ApiModel(value = "用户登录实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

    @ApiModelProperty(value = "登录用户名")
    @NotNull(message = "username不能为null")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotNull(message = "password不能为null")
    private String password;
}
