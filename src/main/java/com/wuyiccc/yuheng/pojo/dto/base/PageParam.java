package com.wuyiccc.yuheng.pojo.dto.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ApiModel(value = "分页参数")
public class PageParam {
    @ApiModelProperty(value = "当前页")
    @NotNull(message = "当前页不可为null")
    @Min(value = 1, message = "current的值最小为1")
    private Integer current = 1;

    @ApiModelProperty(value = "每页条数")
    @NotNull(message = "size不能为null")
    @Min(value = 1, message = "size的值为1")
    private Integer size = 10;


    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "current=" + current +
                ", size=" + size +
                '}';
    }
}

