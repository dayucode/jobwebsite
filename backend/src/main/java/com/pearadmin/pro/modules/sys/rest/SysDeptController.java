package com.pearadmin.pro.modules.sys.rest;

import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.tools.sequence.SequenceUtil;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.module.ResultTable;
import com.pearadmin.pro.common.web.domain.response.module.ResultTree;
import com.pearadmin.pro.modules.sys.domain.SysDept;
import com.pearadmin.pro.modules.sys.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import static com.pearadmin.pro.common.web.domain.response.Result.decide;

/**
 * 部门控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Api(tags = {"部门"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "dept")
public class SysDeptController extends BaseController {

    @Resource
    private SysDeptService sysDeptService;

    /**
     * 查询部门列表
     *
     * @return {@link Result}
     */
    @GetMapping("data")
    @Log(title = "查询部门")
    @ApiOperation(value = "查询部门")
    public ResultTable list(SysDept sysDept){
        return treeTable(sysDeptService.list(sysDept));
    }

    /**
     * Describe: 获取部门树状数据结构
     * Param SysDept
     */
    @GetMapping("tree")
    public ResultTree tree(SysDept param) {
        List<SysDept> data = sysDeptService.list(param);
        return dataTree(data);
    }

    /**
     * 新增部门
     *
     * @param sysDept 部门实体
     */
    @PostMapping("save")
    @Log(title = "新增部门")
    @ApiOperation(value = "新增部门")
    public Result save(@RequestBody SysDept sysDept){
        sysDept.setDeptId(SequenceUtil.makeStringId());
        sysDept.setDeleted(true);
        return auto(sysDeptService.save(sysDept));
    }

    /**
     * 修改部门
     *
     * @param sysDept 部门实体
     */
    @PutMapping("edit")
    @Log(title = "修改部门")
    @ApiOperation(value = "修改部门")
    public Result edit(@RequestBody SysDept sysDept){
        return auto(sysDeptService.updateById(sysDept));
    }

    /**
     * 删除部门
     *
     * @param deptId 部门编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除部门")
    @ApiOperation(value = "删除部门")
    public Result remove(@RequestParam String deptId){

        /// 检 测 是 否 存 在 子 部 门
        if(sysDeptService.lambdaQuery().eq(SysDept::getParentId,deptId).count() > 0) return failure();

        return auto(sysDeptService.removeById(deptId));
    }

    /**
     * 删除部门
     *
     * @param ids 部门编号
     */
    @DeleteMapping("removeBatch")
    public Result removeBatch(@RequestParam List<String> ids) { return auto(sysDeptService.removeByIds(ids)); }

    /**
     * Describe: 根据 Id 禁用用户
     * Param powerId
     * Return ResuTree
     * */
    @PutMapping("enable")
    public Result enable(@RequestBody SysDept sysDept){
        sysDept.setEnable("1");
        boolean result = sysDeptService.updateById(sysDept);
        return decide(result);
    }
    /**
     * Describe: 根据 Id 禁用用户
     * Param powerId
     * Return ResuTree
     * */
    @PutMapping("disable")
    public Result disable(@RequestBody SysDept sysDept){
        sysDept.setEnable("0");
        boolean result = sysDeptService.updateById(sysDept);
        return decide(result);
    }
    @GetMapping("selectParent")
    public ResultTree selectParent(SysDept sysDept){
        List<SysDept> list = sysDeptService.list(sysDept);
        SysDept basePower = new SysDept();
        basePower.setDeptName("顶级组织");
        basePower.setDeptId("0");
        basePower.setParentId("-1");
        list.add(basePower);
        return dataTree(list);
    }
    @PutMapping("update")
    public Result update(@RequestBody SysDept sysDept){
        if(Strings.isBlank(sysDept.getParentId())){
            return failure("请选择上级菜单");
        }
        boolean result = sysDeptService.updateById(sysDept);
        return decide(result);
    }
}