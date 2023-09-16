package com.wuyiccc.yuheng.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author wuyiccc
 * @date 2023/9/13 23:37
 */
@ApiModel(value = "新建用户实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录用户名")
    @NotNull(message = "username不能为null")
    @Length(max = 128, message = "username字符长度不能超过128")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotNull(message = "password不能为null")
    private String password;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    @NotNull(message = "nickname不能为null")
    @Length(max = 128, message = "nickname字符长度不能超过128")
    private String nickname;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @NotNull(message = "remark不能为null")
    @Length(max = 500, message = "remark字符长度不能超过500")
    private String remark;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像url链接")
    @NotNull(message = "faceUrl不能为null")
    @Length(max = 255, message = "faceUrl字符长度不能超过255")
    private String faceUrl;
}
