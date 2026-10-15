package com.alibaba.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 对外暴露属性配置信息
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "alibaba.oss")
public class OssProperties {
        private String regionId;
        private String bucketName;
        private String accessKeyId;
        private String accessKeySecret;
}
