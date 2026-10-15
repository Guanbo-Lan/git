package com.alibaba.core.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.autoconfigure.OssProperties;
import com.alibaba.core.exception.OssException;
import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.OSSClientBuilder;
import com.aliyun.sdk.service.oss2.credentials.CredentialsProvider;
import com.aliyun.sdk.service.oss2.credentials.EnvironmentVariableCredentialsProvider;
import com.aliyun.sdk.service.oss2.models.DeleteObjectRequest;
import com.aliyun.sdk.service.oss2.models.PutObjectRequest;
import com.aliyun.sdk.service.oss2.transport.BinaryData;
import com.aliyun.sdk.service.oss2.transport.ByteArrayBinaryData;

import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * 对外暴露服务
 * 1.文件上传存储
 * 2.文件删除
 */
public class OssTemplate {

    private final OssProperties ossProperties;
    public OssTemplate(OssProperties ossProperties) {
        this.ossProperties = ossProperties;
    }
    public String upload(InputStream file, String fileName) {
        // TODO 优化
        CredentialsProvider provider = new EnvironmentVariableCredentialsProvider();
        OSSClientBuilder clientBuilder = OSSClient.newBuilder()
                .credentialsProvider(provider)
                .region(ossProperties.getRegionId());

        String finalFileName = finalFileName(fileName);

        try (OSSClient client = clientBuilder.build()) {
            byte[] bytes = IoUtil.readBytes(file);
            BinaryData data = new ByteArrayBinaryData(bytes);
            PutObjectRequest request = PutObjectRequest.newBuilder()
                    .bucket(ossProperties.getBucketName())
                    .key(finalFileName)
                    .body(data)
                    .build();
            client.putObject(request);
        } catch (Exception e) {
            throw new OssException("阿里云OSS上传文件异常，异常信息如下：" + e.getMessage());
        }
        String buketDomain = ossProperties.getBucketName() +
                "." +
                "oss" +
                "-" +
                ossProperties.getRegionId() +
                ".aliyuncs.com";
        return "https://" + buketDomain + "/" + finalFileName;
    }

    public void del(String fileName) {
        CredentialsProvider provider = new EnvironmentVariableCredentialsProvider();
        OSSClientBuilder clientBuilder = OSSClient.newBuilder()
                .credentialsProvider(provider)
                .region(ossProperties.getRegionId());
        // 此处删除即可
        //  String finalFileName = finalFileName(fileName);
        try (OSSClient client = clientBuilder.build()) {
            DeleteObjectRequest req = DeleteObjectRequest.newBuilder()
                    .bucket(ossProperties.getBucketName())
                    .key(fileName)
                    .build();
            client.deleteObject(req);
        } catch (Exception e) {
            throw new OssException("阿里云OSS删除文件异常，异常信息如下：" + e.getMessage());
        }

    }

    /**
     * /2026/10/10/随机数+原始文件名
     *
     * @param originalFileName 原始文件名称 11.jpg
     * @return String 比如 /2026/10/10/qweqeqeqwe1233ae11.jpg
     */
    private static String finalFileName(String originalFileName) {
        String dir = DateUtil.format(LocalDateTime.now(), "yyyy/MM/dd");
        String radomNum = UUID.fastUUID().toString();
        return dir + "/" + radomNum + originalFileName;
    }

}
