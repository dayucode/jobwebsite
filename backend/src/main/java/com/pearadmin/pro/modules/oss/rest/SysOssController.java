package com.pearadmin.pro.modules.oss.rest;

import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.module.ResultTable;
import com.pearadmin.pro.modules.oss.domain.SysOss;
import com.pearadmin.pro.modules.oss.param.SysOssRequest;
import com.pearadmin.pro.modules.oss.service.SysOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 文件控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/28
 * */
@Api(tags = {"文件"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "oss")
public class SysOssController extends BaseController {

    @Resource
    private SysOssService sysOssService;

    /**
     * 查询文件列表
     *
     * @param request 查询参数
     *
     * @return {@link String}
     */
    @GetMapping("page")
    @Log(title = "文件列表")
    @ApiOperation(value = "文件列表")
    public ResultTable page(SysOssRequest request){
        PageInfo<SysOss> pageInfo = sysOssService.page(request);
        return pageTable(pageInfo.getList(),pageInfo.getTotal());
    }

    /**
     * 文件上传
     *
     * @param file 上传文件
     *
     * @return {@link String}
     */
    @PostMapping("upload")
    @ApiOperation(value = "文件上传")
    public Result upload(@RequestParam("file") MultipartFile file) {
        if( file.isEmpty()) { return failure(); }
        return success(sysOssService.upload(file));
    }

    /**
     * 文件上传
     *
     * @param request 上传文件
     */
    @PostMapping("multipartUpload")
    @ApiOperation(value = "多文件上传")
    public Result multipartUpload(HttpServletRequest request){
        return success(sysOssService.upload(((MultipartHttpServletRequest) request).getFiles("file")));
    }
}
