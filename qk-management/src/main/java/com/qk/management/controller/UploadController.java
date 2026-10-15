package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.management.service.UploadService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Iterator;

/**
 *
 * @author MrZhao
 * 文件上传Controller
 *
 */
@RestController
@Slf4j
@SuppressWarnings("all")
@RequestMapping(path = "/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 上传文件（头像）
     * 兼容前端不同的表单字段名：file / image / avatar / upload 等，取请求中的第一个文件
     */
    @PostMapping
    public Result upload(HttpServletRequest request,
                         @RequestParam(value = "file", required = false) MultipartFile file) {
        // 未按 "file" 字段提交时，从 multipart 请求中取第一个文件
        if (file == null && request instanceof MultipartHttpServletRequest multipartRequest) {
            Iterator<String> fileNames = multipartRequest.getFileNames();
            if (fileNames.hasNext()) {
                file = multipartRequest.getFile(fileNames.next());
            }
        }
        if (file == null || file.isEmpty()) {
            return Result.error("请选择要上传的文件");
        }
        String path = uploadService.upload(file);
        // 返回完整可访问地址（带协议、主机、端口），避免前端把相对路径解析到自己域名下导致 404
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(path)
                .toUriString();
        return Result.success(url);
    }
}
