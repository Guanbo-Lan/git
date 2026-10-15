package com.alibaba.oss;
import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.OSSClientBuilder;
import com.aliyun.sdk.service.oss2.credentials.CredentialsProvider;
import com.aliyun.sdk.service.oss2.credentials.EnvironmentVariableCredentialsProvider;
import com.aliyun.sdk.service.oss2.models.BucketSummary;
import com.aliyun.sdk.service.oss2.models.ListBucketsRequest;
import com.aliyun.sdk.service.oss2.models.ListBucketsResult;
import com.aliyun.sdk.service.oss2.paginator.ListBucketsIterable;
/**
 * 验证测试阿里云OSS 提供SDK 功能
 */
public class OssTests {
    public static void main(String[] args) {
        String region = "cn-hangzhou";

        CredentialsProvider provider = new EnvironmentVariableCredentialsProvider();
        OSSClientBuilder clientBuilder = OSSClient.newBuilder()
                .credentialsProvider(provider)
                .region(region);

        try (OSSClient client = clientBuilder.build()) {

            ListBucketsIterable paginator = client.listBucketsPaginator(
                    ListBucketsRequest.newBuilder()
                            .build());

            for (ListBucketsResult result : paginator) {
                for (BucketSummary info : result.buckets()) {
                    System.out.printf("bucket: name:%s, region:%s, storageClass:%s\n", info.name(), info.region(), info.storageClass());
                }
            }

        } catch (Exception e) {
//            ServiceException se = ServiceException.asCause(e);//            if (se != null) {//                System.out.printf("ServiceException: requestId:%s, errorCode:%s\n", se.requestId(), se.errorCode());//            }
            System.out.printf("error:\n%s", e);
        }
    }
}
