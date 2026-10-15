package com.qk.management.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.qk.common.exception.QkException;
import com.qk.management.service.UploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Set;

/**
 * @author MrZhao
 * 文件上传业务层实现类
 */
@Service
public class UploadServiceImpl implements UploadService {

    // 上传目录，可通过配置 qk.upload.dir 覆盖，默认项目根目录下的 upload
    @Value("${qk.upload.dir:./upload}")
    private String uploadDir;

    // 允许上传的图片类型（头像）
    private static final Set<String> ALLOWED_EXT = Set.of("jpg", "jpeg", "png", "gif", "webp", "bmp");

    @Override
    public String upload(MultipartFile file) {
        // 防御性校验：文件为空则直接抛参数非法，避免 NPE
        if (file == null || file.isEmpty()) {
            throw new QkException("请选择要上传的文件");
        }
        // 1. 校验文件类型，仅允许图片
        String suffix = StrUtil.emptyToDefault(FileUtil.extName(file.getOriginalFilename()), "").toLowerCase();
        String contentType = file.getContentType();
        boolean isImage = StrUtil.isNotBlank(contentType) && contentType.startsWith("image/");
        if (!isImage && !ALLOWED_EXT.contains(suffix)) {
            throw new QkException("仅支持上传图片文件");
        }
        // 2. 生成唯一文件名，避免重名覆盖
        String newFileName = IdUtil.fastSimpleUUID() + (StrUtil.isBlank(suffix) ? "" : "." + suffix);
        // 3. 确保上传目录存在（转绝对路径，与静态资源映射目录保持一致）
        File dir = FileUtil.mkdir(new File(uploadDir).getAbsoluteFile());
        File targetFile = FileUtil.file(dir, newFileName);
        // 4. 保存文件
        try {
            FileUtil.writeFromStream(file.getInputStream(), targetFile);
        } catch (Exception e) {
            throw new QkException("文件上传失败");
        }
        // 5. 返回可访问路径
        return "/upload/" + newFileName;
    }
}
