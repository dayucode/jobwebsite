package com.pearadmin.pro.modules.oss.cloud;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    /**
     * 文件上传
     *
     * @param file 文件
     *
     * @return {@link String} 存储路径
     * */
    String upload(MultipartFile file);
}
