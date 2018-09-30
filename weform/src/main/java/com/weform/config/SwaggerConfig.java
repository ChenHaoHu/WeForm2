package com.weform.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
 
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .host("weform.hcyang.top")
                .apiInfo(apiInfo())
                .select()
                // 这里是全局扫描有@Api注解得类，还可以扫描任意位置，指定包以及针对方法上的指定注解
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }
 
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("WEFORM API文档")
                .description("提供表单报名分享服务的好程序")
//                .termsOfServiceUrl("")
//                .contact(new Contact("作者:胡小白", "", "775656764@qq.com"))
//                .license("")
//                .licenseUrl("")
//                .version("1.0.1")
                .build();
    }
 
}
