package com.slcp.devops.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.slcp.devops.entity.SysAdmin;
import com.slcp.devops.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充字段
 *
 * @author devops
 */
@Slf4j
@Component
public class DevOpsMetaObjectHandler implements MetaObjectHandler {

    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";
    private static final String TOKEN = "token";

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, CREATE_TIME, LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
        Object originalObject = metaObject.getOriginalObject();
        if (originalObject instanceof SysAdmin) {
            this.strictInsertFill(metaObject, TOKEN, String.class, TokenUtils.getToken(((SysAdmin) originalObject).getId()));
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
    }
}
