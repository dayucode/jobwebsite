package com.pearadmin.pro.modules.job.rest;

import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.web.domain.response.module.ResultTable;
import com.pearadmin.pro.modules.job.domain.SysJob;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.modules.job.param.SysJobRequest;
import com.pearadmin.pro.modules.job.service.SysJobService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 任务控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/15
 * */
@Api(tags = {"任务"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "job")
public class SysJobController extends BaseController {

    @Resource
    private SysJobService sysJobService;

    /**
     * 查询任务列表 (分页)
     *
     * @param request 查询参数
     */
    @GetMapping("page")
    @Log(title = "查询任务")
    @ApiOperation(value = "查询任务")
    public ResultTable page(SysJobRequest request){
        PageInfo<SysJob> pageInfo = sysJobService.page(request);
        return pageTable(pageInfo.getList(),pageInfo.getTotal());
    }

    /**
     * 新增任务
     *
     * @param sysJob 任务信息
     * */
    @PostMapping("save")
    @Log(title = "新增任务")
    @ApiOperation(value = "新增任务")
    public Result save(@RequestBody SysJob sysJob) {
        return auto(sysJobService.save(sysJob));
    }

    /**
     * 修改任务
     *
     * @param sysJob 任务信息
     * */
    @PutMapping("edit")
    @Log(title = "修改任务")
    @ApiOperation(value = "修改任务")
    public Result edit(@RequestBody SysJob sysJob) {
        return auto(sysJobService.updateById(sysJob));
    }

    /**
     * 立即执行
     *
     * @param id 任务编号
     * */
    @PutMapping("run")
    @Log(title = "立即执行")
    @ApiOperation(value = "立即执行")
    public Result run(@RequestParam("id") String id) {
        return auto(sysJobService.run(id));
    }

    /**
     * 恢复任务
     *
     * @param id 任务编号
     * */
    @PutMapping("resume")
    @Log(title = "恢复任务")
    @ApiOperation(value = "恢复任务")
    public Result resume(@RequestParam("id") String id) {
        return auto(sysJobService.resume(id));
    }

    /**
     * 暂停任务
     *
     * @param id 任务编号
     * */
    @PutMapping("pause")
    @Log(title = "暂停任务")
    @ApiOperation(value = "暂停任务")
    public Result pause(@RequestParam("id") String id) {
        return auto(sysJobService.pause(id));
    }


    /**
     * 删除任务
     *
     * @param id 任务编号
     * */
    @DeleteMapping("remove")
    @Log(title = "删除任务")
    @ApiOperation(value = "删除任务")
    public Result remove(@RequestParam("id") String id) {
        return success(sysJobService.removeById(id));
    }

    /**
     * 任务编号
     *
     * @param ids 任务编号
     * */
    @DeleteMapping("removeBatch")
    @Log(title = "删除任务")
    @ApiOperation(value = "删除任务")
    public Result removeBatch(@RequestParam("ids") List<String> ids) {
        return success(sysJobService.removeByIds(ids));
    }

}
