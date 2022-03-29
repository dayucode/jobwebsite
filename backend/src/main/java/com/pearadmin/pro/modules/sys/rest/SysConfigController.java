package com.pearadmin.pro.modules.sys.rest;

import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.aop.annotation.Repeat;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.module.ResultTable;
import com.pearadmin.pro.modules.sys.domain.SysConfig;
import com.pearadmin.pro.modules.sys.param.SysConfigRequest;
import com.pearadmin.pro.modules.sys.service.SysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 配置控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@Api(tags = {"配置"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "config")
public class SysConfigController extends BaseController {

    @Resource
    private SysConfigService sysConfigService;

    /**
     * 查询配置列表
     *
     * @param request 查询参数
     * @return {@link Result}
     */
    @GetMapping("page")
    @Log(title = "配置列表")
    @ApiOperation(value = "配置列表")
    public ResultTable page(SysConfigRequest request){
        PageInfo<SysConfig> pageInfo = sysConfigService.page(request);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 新增配置
     *
     * @param sysConfig 配置实体
     */
    @Repeat
    @PostMapping("save")
    @Log(title = "新增配置")
    @ApiOperation(value = "新增配置")
    public Result save(@RequestBody @Validated SysConfig sysConfig) {
        return auto(sysConfigService.save(sysConfig));
    }

    /**
     * 修改配置
     *
     * @param sysConfig 配置实体
     */
    @PutMapping("edit")
    @Log(title = "修改配置")
    @ApiOperation(value = "修改配置")
    public Result edit(@RequestBody SysConfig sysConfig) {
        return auto(sysConfigService.updateById(sysConfig));
    }

    /**
     * 删除配置
     *
     * @param id 配置编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除配置")
    @ApiOperation(value = "删除配置")
    public Result remove(@RequestParam String id){
        return auto(sysConfigService.removeById(id));
    }

    /**
     * 删除配置
     *
     * @param ids 配置编号
     * */
    @DeleteMapping("removeBatch")
    @Log(title = "删除配置")
    @ApiOperation(value = "删除配置")
    public Result removeBatch(@RequestParam List<String> ids) { return auto(sysConfigService.removeByIds(ids)); }

}
