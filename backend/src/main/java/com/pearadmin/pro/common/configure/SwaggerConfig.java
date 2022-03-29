package com.pearadmin.pro.common.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;

/**
 * Swagger 文档配置
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@Configuration
@EnableSwagger2
public class SwaggerConfig  {

    private static final String BASE_PACKAGE = "com.pearadmin";

    private static final String DEFAULT_GROUP = "default";

    private static final Boolean ENABLE = true;

    @Bean
    public Docket docker(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName(DEFAULT_GROUP)
                .enable(ENABLE)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Pear Admin API",
                "Spring Boot 企业级开发平台" ,
                "",
                "www.pearadmin.com",new Contact("pear-admin","https://gitee.com/pear-admin","jmys1992@gmail.com"),
                "apache license",
                "https://gitee.com/pear-admin",
                new ArrayList<>()
        );
    }

}
