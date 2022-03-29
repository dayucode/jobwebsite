package com.pearadmin.pro.modules.not.rest;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.module.ResultTable;
import com.pearadmin.pro.modules.not.domain.SysInbox;
import com.pearadmin.pro.modules.not.service.SysInboxService;
import io.swagger.annotations.Api;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.modules.not.param.SysInboxRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 私信控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/28
 * */
@Api(tags = {"私信"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "inbox")
public class SysInboxController extends BaseController {

    @Resource
    private SysInboxService sysInboxService;

    /**
     * 查询私信
     *
     * @param request 查询参数
     * @return {@link Result}
     */
    @GetMapping("page")
    @Log(title = "查询私信")
    @ApiOperation(value = "查询私信")
    public ResultTable page(SysInboxRequest request){
        PageInfo<SysInbox> pageInfo = sysInboxService.page(request);
        return pageTable(pageInfo.getList(),pageInfo.getTotal());
    }

    /**
     * 新增私信
     *
     * @param sysInbox 私信实体
     * @return {@link Result}
     */
    @PostMapping("save")
    @Log(title = "新增私信")
    @ApiOperation(value = "新增私信")
    public Result save(@RequestBody SysInbox sysInbox) {
        sysInbox.setDialogueId(IdWorker.getIdStr());
        return auto(sysInboxService.save(sysInbox));
    }

    /**
     * 修改私信
     *
     * @param sysInbox 私信实体
     * @return {@link Result}
     */
    @PutMapping("edit")
    @Log(title = "修改私信")
    @ApiOperation(value = "修改私信")
    public Result edit(@RequestBody SysInbox sysInbox) {
        return auto(sysInboxService.updateById(sysInbox));
    }

    /**
     * 删除私信
     *
     * @param id 私信编号
     * @return {@link Result}
     */
    @DeleteMapping("remove")
    @Log(title = "删除私信")
    @ApiOperation(value = "删除私信")
    public Result remove(@RequestParam String id) {
        return auto(sysInboxService.removeById(id));
    }

    /**
     * 批量删除
     *
     * @param ids 私信编号
     * @return {@link Result}
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(sysInboxService.removeByIds(ids));
    }

}
