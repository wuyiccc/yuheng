package com.wuyiccc.yuheng.pojo.vo.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;


@ApiModel(value = "分页结果")
public class PageResult<T> {
    @ApiModelProperty(value = "总页数")
    private long page;

    @ApiModelProperty(value = "当前页")
    private long current;

    @ApiModelProperty(value = "每页显示条数")
    private long size;

    @ApiModelProperty(value = "总条数")
    private long total;

    @ApiModelProperty(value = "具体信息")
    private List<T> records;

    public PageResult() {
    }

    public static <T> PageResult<T> page() {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setRecords(new ArrayList<>());
        return pageResult;
    }

    public static <T> PageResult<T> page(List<T> content, Page page) {
        return page(content, page.getCurrent(), page.getSize(), page.getTotal());
    }

    public static <T> PageResult<T> page(List<T> content, int current, int size, long total) {
        return page(content, current, (long) size, total);
    }

    public static <T> PageResult<T> page(List<T> content, int current, int size, int total) {
        return page(content, current, (long) size, total);
    }

    public static <T> PageResult<T> page(List<T> content, long current, long size, long total) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setRecords(content);
        pageResult.setSize(size);
        pageResult.setTotal(total);
        pageResult.setCurrent(current);
        if (size != 0) {
            if (total % size != 0) {
                pageResult.setPage(total / size + 1);
            } else {
                pageResult.setPage(total / size);
            }
        }
        return pageResult;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "page=" + page +
                ", current=" + current +
                ", size=" + size +
                ", total=" + total +
                ", records=" + records +
                '}';
    }

    public long getPage() {
        return this.page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }
}
