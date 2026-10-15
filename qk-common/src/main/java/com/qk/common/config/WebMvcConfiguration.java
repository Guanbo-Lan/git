package com.qk.common.config;

import com.qk.common.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 *
 * @author MrZhao
 *  WebMvc 通用配置类
 *  1. 将 /upload/** 映射到本地上传目录，使上传的图片可通过 URL 访问
 *  2. 注册登录拦截器，对除登录与静态资源外的请求进行鉴权
 *
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    // 上传目录，与 UploadServiceImpl 保持一致
    @Value("${qk.upload.dir:./upload}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 用 toURI() 生成标准的 file:// URL，避免 Windows 反斜杠导致资源定位失败
        String location = new File(uploadDir).getAbsoluteFile().toURI().toString();
        // 主动补上尾部斜杠，避免 Spring 打印 "Appended trailing slash" 警告
        if (!location.endsWith("/")) {
            location = location + "/";
        }
        registry.addResourceHandler("/upload/**")
                .addResourceLocations(location);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/upload/**", "/error");
    }
}
