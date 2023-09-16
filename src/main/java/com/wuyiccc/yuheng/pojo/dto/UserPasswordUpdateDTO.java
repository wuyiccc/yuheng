package com.wuyiccc.yuheng.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author wuyiccc
 * @date 2023/9/16 10:05
 */
@ApiModel(value = "用户密码更新实体")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordUpdateDTO {

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "用户密码")
    @NotNull(message = "用户密码不能为null")
    private String password;
}
