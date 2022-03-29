package com.pearadmin.pro.modules.not.rest;

import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.module.ResultTable;
import com.pearadmin.pro.modules.not.domain.SysAnnounce;
import com.pearadmin.pro.modules.not.param.SysAnnounceRequest;
import com.pearadmin.pro.modules.not.service.SysAnnounceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 公告控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/28
 * */
@Api(tags = {"公告"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "announce")
public class SysAnnounceController extends BaseController {

    @Resource
    private SysAnnounceService sysAnnounceService;

    /**
     * 查询公告
     *
     * @param request 查询参数
     * @return {@link Result}
     */
    @GetMapping("page")
    @Log(title = "公告列表")
    @ApiOperation(value = "公告列表")
    public ResultTable page(SysAnnounceRequest request){
        PageInfo<SysAnnounce> pageInfo = sysAnnounceService.page(request);
        return pageTable(pageInfo.getList(),pageInfo.getTotal());
    }

    /**
     * 新增公告
     *
     * @param sysAnnounce 查询参数
     * @return {@link Result}
     */
    @PostMapping("save")
    @Log(title = "新增公告")
    @ApiOperation(value = "新增公告")
    public Result save(@RequestBody SysAnnounce sysAnnounce){
        return auto(sysAnnounceService.save(sysAnnounce));
    }

    /**
     * 修改公告
     *
     * @param sysAnnounce 查询参数
     * @return {@link Result}
     */
    @PutMapping("edit")
    @Log(title = "修改公告")
    @ApiOperation(value = "修改公告")
    public Result edit(@RequestBody SysAnnounce sysAnnounce){ return auto(sysAnnounceService.updateById(sysAnnounce)); }

    /**
     * 删除公告
     *
     * @param id 公告编号
     * @return {@link Result}
     */
    @DeleteMapping("remove")
    @Log(title = "删除公告")
    @ApiOperation(value = "删除公告")
    public Result remove(@RequestParam String id){
        return auto(sysAnnounceService.removeById(id));
    }

    /**
     * 批量删除
     *
     * @param ids 批量删除
     * @return {@link Result}
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids){
        return auto(sysAnnounceService.removeByIds(ids));
    }

}
