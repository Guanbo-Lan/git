package com.qk.management.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author MrZhao
 * 文件上传的业务层接口
 */
public interface UploadService {
    /**
     * 上传文件
     *
     * @param file 上传的文件
     * @return 文件可访问路径
     */
    String upload(MultipartFile file);
}
