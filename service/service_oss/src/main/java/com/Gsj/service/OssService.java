package com.Gsj.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    /**
     * 上传文件到oss
     * @param file 文件
     * @return 返回文件的url地址
     */
    String uploadFileAvatar(MultipartFile file);
}
