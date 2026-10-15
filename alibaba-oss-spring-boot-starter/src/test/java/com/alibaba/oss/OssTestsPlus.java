package com.alibaba.oss;
import cn.hutool.core.io.IoUtil;
import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.OSSClientBuilder;
import com.aliyun.sdk.service.oss2.credentials.CredentialsProvider;
import com.aliyun.sdk.service.oss2.credentials.EnvironmentVariableCredentialsProvider;
import com.aliyun.sdk.service.oss2.models.*;
import com.aliyun.sdk.service.oss2.transport.BinaryData;
import com.aliyun.sdk.service.oss2.transport.ByteArrayBinaryData;

import java.io.FileInputStream;

public class OssTestsPlus {
    public static void main(String[] args) {
        String region = "cn-hangzhou";

        CredentialsProvider provider = new EnvironmentVariableCredentialsProvider();
        OSSClientBuilder clientBuilder = OSSClient.newBuilder()
                .credentialsProvider(provider)
                .region(region);

        try (OSSClient client = clientBuilder.build()) {

                // hutool 工具包 IOUtil 读取文件
                FileInputStream in = new FileInputStream("D:\\11.png");

                byte[] bytes = IoUtil.readBytes(in);

                BinaryData data = new ByteArrayBinaryData(bytes);
                // 上传 或者 下载
                PutObjectRequest request = PutObjectRequest.newBuilder()
                        .bucket("yuuuuk1")
                        .key("abc")
                        .body(data)
                        .build();
                client.putObject(request); // 文件上传存储


                /*DeleteObjectRequest req = DeleteObjectRequest.newBuilder()
                        .bucket("yuuuuk1")
                        .key("11.png")
                        .build();
                client.deleteObject(req); // 文件删除*/
        } catch (Exception e) {
            System.out.printf("error:\n%s", e);
        }
    }
}
