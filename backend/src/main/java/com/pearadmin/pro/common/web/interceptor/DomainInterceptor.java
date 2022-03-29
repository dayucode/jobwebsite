package com.pearadmin.pro.common.web.interceptor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.pearadmin.pro.common.context.UserContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 字 段 填 充 拦 截 器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2020/10/23
 * */
@Component
public class DomainInterceptor implements MetaObjectHandler {

    @Resource
    private UserContext userContext;

    @Override
    public void insertFill(MetaObject metaObject) {
        createField(metaObject);
        updateField(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        updateField(metaObject);
    }

    /**
     * @Field 创建人
     * @Field 创建时间
     * */
    public void createField(MetaObject metaObject){
        //放行未认证接口方法
        try {
            userContext.getPrincipal();
            this.strictInsertFill(metaObject,"createBy", String.class, userContext.getUserId());
        }catch (RuntimeException e){

        }
        finally {
            this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        }
    }

    /**
     * @Field 修改人
     * @Field 修改时间
     * */
    public void updateField(MetaObject metaObject){
        //放行未认证接口方法
        try {
            userContext.getPrincipal();
            this.strictInsertFill(metaObject,"updateBy", String.class, userContext.getUserId());
        }catch (RuntimeException e){

        }
        finally {
            this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        }
    }

}
