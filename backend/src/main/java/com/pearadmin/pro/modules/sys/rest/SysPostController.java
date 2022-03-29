package com.pearadmin.pro.modules.sys.rest;

import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.module.ResultTable;
import com.pearadmin.pro.modules.sys.domain.SysPost;
import com.pearadmin.pro.modules.sys.param.SysPostRequest;
import com.pearadmin.pro.modules.sys.service.SysPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 岗位控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Api(tags = {"岗位"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "post")
public class SysPostController extends BaseController {

    @Resource
    private SysPostService sysPostService;

    /**
     * 查询岗位列表
     *
     * @param request 查询参数
     */
    @GetMapping("page")
    @Log(title = "查询岗位")
    @ApiOperation(value = "查询岗位")
    public ResultTable page(SysPostRequest request){
        PageInfo<SysPost> pageInfo = sysPostService.page(request);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 查询岗位列表
     *
     * @param request 查询参数
     */
    @GetMapping("list")
    @Log(title = "查询岗位")
    @ApiOperation(value = "查询岗位")
    public Result list(SysPostRequest request) {
        return success(sysPostService.list(request));
    }

    /**
     * 新增岗位
     *
     * @param sysPost 角色实体
     *
     * @return {@link Boolean}
     */
    @PostMapping("save")
    @Log(title = "新增岗位")
    @ApiOperation(value = "新增岗位")
    public Result save(@RequestBody SysPost sysPost){
        return auto(sysPostService.save(sysPost));
    }

    /**
     * 修改岗位
     *
     * @param sysPost 角色实体
     *
     * @return {@link Boolean}
     */
    @PutMapping("edit")
    @Log(title = "修改岗位")
    @ApiOperation(value = "修改岗位")
    public Result edit( @RequestBody SysPost sysPost){
        return auto(sysPostService.updateById(sysPost));
    }

    /**
     * 删除岗位
     *
     * @param id 岗位编号
     *
     * @return {@link Boolean}
     */
    @DeleteMapping("remove")
    @Log(title = "删除岗位")
    @ApiOperation(value = "删除岗位")
    public Result remove(@RequestParam String id){
        return auto(sysPostService.removeById(id));
    }

    /**
     * 批量删除
     *
     * @param ids 岗位编号
     *
     * @return {@link Boolean}
     */
    @DeleteMapping("removeBatch")
    @Log(title = "删除岗位")
    @ApiOperation(value = "删除岗位")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(sysPostService.removeByIds(ids));
    }

}