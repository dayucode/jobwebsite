package com.pearadmin.pro.modules.sys.rest;

import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.aop.annotation.Excel;
import com.pearadmin.pro.common.aop.enums.Action;
import com.pearadmin.pro.common.aop.enums.Model;
import com.pearadmin.pro.common.web.domain.response.module.ResultTable;
import com.pearadmin.pro.modules.sys.domain.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.modules.sys.param.SysLogRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.pearadmin.pro.modules.sys.service.SysLogService;
import com.pearadmin.pro.common.constant.ControllerConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.Result;
import javax.annotation.Resource;
import java.util.List;

/**
 * 日志控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Api(tags = {"日志"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "log")
public class SysLogController extends BaseController {

    @Resource
    private SysLogService sysLogService;

    /**
     * 查询日志列表
     *
     * @param request 查询参数
     */
    @GetMapping("page")
    @Log(title = "查询日志")
    @ApiOperation(value = "查询日志")
    public ResultTable page(SysLogRequest request){
        PageInfo<SysLog> pageInfo = sysLogService.page(request);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 清空日志
     *
     * @param isAuth 日志类型
     */
    @DeleteMapping("clean")
    @Log(title = "清空日志")
    @ApiOperation(value = "清空日志")
    public Result clean(Boolean isAuth){
        if(isAuth) {
            return auto(sysLogService.lambdaUpdate()
                    .eq(com.pearadmin.pro.modules.sys.domain.SysLog::getAction, Action.AUTH)
                    .remove());
        } else {
            return auto(sysLogService.lambdaUpdate()
                    .ne(com.pearadmin.pro.modules.sys.domain.SysLog::getAction, Action.AUTH)
                    .remove());
        }
    }

    @GetMapping("/export")
    @Log(title = "导出日志")
    @ApiOperation(value = "导出日志")
    @Excel(clazz = SysLog.class, model = Model.WRITE)
    public List<SysLog> export() {
        return sysLogService.list(new SysLogRequest());
    }
}
