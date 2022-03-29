package com.pearadmin.pro.modules.sys.rest;

import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.module.ResultTable;
import com.pearadmin.pro.modules.sys.domain.SysDataSource;
import com.pearadmin.pro.modules.sys.param.SysDataSourceRequest;
import com.pearadmin.pro.modules.sys.service.SysDataSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = {"多库"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "dataSource")
public class SysDataSourceController extends BaseController {

    @Resource
    private SysDataSourceService sysDataSourceService;

    /**
     * 查询多库列表
     *
     * @param request 查询参数
     * @return {@link Result}
     */
    @GetMapping("page")
    @Log(title = "查询多库")
    @ApiOperation(value = "查询多库")
    public ResultTable page(SysDataSourceRequest request) {
        PageInfo<SysDataSource> pageInfo = sysDataSourceService.page(request);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 新增多库
     *
     * @param sysDataSource 多库实体
     */
    @PostMapping("save")
    @Log(title = "新增多库")
    @ApiOperation(value = "新增多库")
    public Result save(@RequestBody SysDataSource sysDataSource) {
        return auto(sysDataSourceService.save(sysDataSource));
    }

    /**
     * 修改多库
     *
     * @param sysDataSource 多库实体
     */
    @PutMapping("edit")
    @Log(title = "修改多库")
    @ApiOperation(value = "修改多库")
    public Result edit(@RequestBody SysDataSource sysDataSource) {
        return auto(sysDataSourceService.updateById(sysDataSource));
    }

    /**
     * 删除多库
     *
     * @param id 多库编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除多库")
    @ApiOperation(value = "删除多库")
    public Result remove(String id) {
        return auto(sysDataSourceService.removeById(id));
    }

    /**
     * 删除多库
     *
     * @param ids 多库编号
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(sysDataSourceService.removeByIds(ids));
    }
}
