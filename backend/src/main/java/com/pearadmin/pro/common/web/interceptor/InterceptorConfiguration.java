package com.pearadmin.pro.common.web.interceptor;

import com.pearadmin.pro.common.constant.TenantConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Configuration
public class InterceptorConfiguration implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.addMyInterceptor();
    }

    private void addMyInterceptor() {
        System.out.println("=======eeeeeeeeee=======");
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(new DataScopeInterceptor());
            if(TenantConstant.enable) {
                log.info("Enable multi tenant");
                sqlSessionFactory.getConfiguration().addInterceptor(new TenantInterceptor());
            }
        }
    }
}
