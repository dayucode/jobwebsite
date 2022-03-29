package com.pearadmin.pro.modules.job.rest;

import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.module.ResultTable;
import com.pearadmin.pro.modules.job.domain.SysJobLog;
import com.pearadmin.pro.modules.job.param.SysJobLogRequest;
import com.pearadmin.pro.modules.job.service.SysJobLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 任务日志控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/15
 * */
@Api(tags = {"任务"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "job/log")
public class SysJobLogController extends BaseController {

    @Resource
    private SysJobLogService sysJobLogService;

    /**
     * 查询任务日志列表 (分页)
     *
     * @param request 查询参数
     */
    @GetMapping("page")
    @Log(title = "查询日志")
    @ApiOperation(value = "任务日志")
    public ResultTable page(SysJobLogRequest request){
        PageInfo<SysJobLog> pageInfo = sysJobLogService.page(request);
        return pageTable(pageInfo.getList(),pageInfo.getTotal());
    }

}
