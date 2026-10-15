package com.alibaba.autoconfigure;

import com.alibaba.core.service.OssTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置类
 */
@Configuration
@EnableConfigurationProperties(value = OssProperties.class)
public class OssAutoConfiguration {
    @Bean
    public OssTemplate ossTemplate(OssProperties ossProperties){
        return new OssTemplate(ossProperties);
    }

}
