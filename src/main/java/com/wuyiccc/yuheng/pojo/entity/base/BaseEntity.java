package com.wuyiccc.yuheng.pojo.entity.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wuyiccc
 * @date 2023/9/12 23:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;


    @TableField(fill = FieldFill.INSERT)
    private String createId;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateId;

    private Integer delFlag;

    private String delId;
}
