package com.wuyiccc.yuheng.config;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author wuyiccc
 * @date 2023/9/12 23:33
 * mybatis-plus自动字段注入
 */
@Component
public class MybatisPlusHandlerConfig implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("gmtCreate", LocalDateTime.now(), metaObject);
        this.setFieldValByName("gmtModified", LocalDateTime.now(), metaObject);

        this.setFieldValByName("updateId", StpUtil.getLoginIdAsString(), metaObject);
        this.setFieldValByName("createId", StpUtil.getLoginIdAsString(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateId", StpUtil.getLoginIdAsString(), metaObject);
    }
}
