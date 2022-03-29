package com.pearadmin.pro.modules.oss.cloud.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.CannedAccessControlList;
import com.pearadmin.pro.modules.oss.cloud.StorageService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 阿里云 Oss 存储服务
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/21
 * */
@Component
public class CloudStorageService implements StorageService {

    @Resource
    private CloudStorageConfig cloudStorageConfig;

    @Resource
    private OSS ossClient;

    public String upload(MultipartFile file)
    {
        try {

            /// 默认配置
            String bucketName = cloudStorageConfig.getBucketName();
            String endpoint = cloudStorageConfig.getEndpoint();

            /// 检验空间
            if(ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }

            /// 存储路径
            String original = file.getOriginalFilename();
            String suffix = original.substring(original.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString();
            String newName = fileName + suffix;
            InputStream inputStream = file.getInputStream();

            ossClient.putObject(bucketName,newName,inputStream);

            /// 访问路径
            return "http://" + bucketName + "." + endpoint + "/" + newName;

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
