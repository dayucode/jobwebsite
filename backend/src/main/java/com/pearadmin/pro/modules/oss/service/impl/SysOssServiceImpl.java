package com.pearadmin.pro.modules.oss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.constant.SystemConstant;
import com.pearadmin.pro.modules.oss.cloud.StorageConfig;
import com.pearadmin.pro.modules.oss.cloud.impl.CloudStorageConfig;
import com.pearadmin.pro.modules.oss.cloud.impl.CloudStorageService;
import com.pearadmin.pro.modules.oss.domain.SysOss;
import com.pearadmin.pro.modules.oss.repository.SysOssRepository;
import com.pearadmin.pro.modules.oss.param.SysOssRequest;
import com.pearadmin.pro.modules.oss.service.SysOssService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.*;

@Service
public class SysOssServiceImpl extends ServiceImpl<SysOssRepository, SysOss> implements SysOssService {

    @Resource
    private StorageConfig storageConfig;

    @Resource
    private CloudStorageConfig cloudStorageConfig;

    @Resource
    private CloudStorageService cloudStorageService;

    @Resource
    private SysOssRepository sysOssRepository;

    @Override
    public List<SysOss> list(SysOssRequest request) {
        return sysOssRepository.selectFile(request);
    }

    @Override
    public PageInfo<SysOss> page(SysOssRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());
        List<SysOss> list = sysOssRepository.selectFile(request);
        return new PageInfo<>(list);
    }

    @Override
    @Transactional
    public List<String> upload(List<MultipartFile> files) {
        List<String> urls = new ArrayList<>();
        files.forEach(file -> {
            urls.add(upload(file));
        });
        return urls;
    }

    @Override
    @Transactional
    public String upload(MultipartFile file) {

        /// 基础信息
        SysOss sysOss = parseInfo(file);

        /// 本地存储
        if(storageConfig.getLocation().equals(SystemConstant.ALIYUN)) {

            /// 阿里存储
            String filePath = cloudStorageService.upload(file);

            /// 附加信息
            sysOss.setPath(filePath);
            sysOss.setBucket(cloudStorageConfig.getBucketName());

        }

        /// 存 储 记 录
        sysOssRepository.insert(sysOss);

        return null;
    }

    /**
     * 解析文件信息
     *
     * @param file 文件实体
     *
     * @return {@link SysOss} 文件信息
     * */
    private SysOss parseInfo(MultipartFile file){

        String fileName = file.getOriginalFilename();

        SysOss sysOss = new SysOss();
        sysOss.setLocation(storageConfig.getLocation());
        sysOss.setName(file.getOriginalFilename());
        sysOss.setSuffix(fileName.substring(fileName.lastIndexOf(".")));
        sysOss.setSize(file.getSize() / 1024);

        return sysOss;
    }
}
