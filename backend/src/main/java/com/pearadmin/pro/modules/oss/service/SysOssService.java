package com.pearadmin.pro.modules.oss.service;

import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.oss.domain.SysOss;
import com.pearadmin.pro.modules.oss.param.SysOssRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SysOssService {

    /**
     * 获取文件列表
     *
     * @param request 查询参数
     * */
    List<SysOss> list(SysOssRequest request);

    /**
     * 获取文件列表
     *  @param request 查询参数
     * @return*/
    PageInfo<SysOss> page(SysOssRequest request);

    /**
     * 文件上传
     *
     * @param file 文件本身
     * */
    String upload(MultipartFile file);

    /**
     * 多文件上传
     *
     * @param files 文件列表
     * */
    List<String> upload(List<MultipartFile> files);

}
