package com.weform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableSwagger2
public class WeformApplication  {

    public static void main(String[] args) {
        SpringApplication.run(WeformApplication.class, args);
    }
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大       
        factory.setMaxFileSize("102400KB"); //KB,MB       
        // / 设置总上传数据总大小       
         factory.setMaxRequestSize("1024000KB");
         return factory.createMultipartConfig();
    }
}
