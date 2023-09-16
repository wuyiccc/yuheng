package com.wuyiccc.yuheng.pojo.dto;

import com.wuyiccc.yuheng.pojo.dto.base.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wuyiccc
 * @date 2023/9/15 23:37
 */
@ApiModel(value = "用户分页查询参数")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPageQueryDTO extends PageParam {

    @ApiModelProperty("用户名")
    private String username;
}
