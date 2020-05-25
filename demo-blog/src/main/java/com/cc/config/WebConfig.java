package com.cc.config;

import com.cc.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration//配置类
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")//拦截admin路径下的所有页面
                .excludePathPatterns("/admin")//不拦截admin路径
                .excludePathPatterns("/admin/login");//不拦截admin/login路径
    }
}
