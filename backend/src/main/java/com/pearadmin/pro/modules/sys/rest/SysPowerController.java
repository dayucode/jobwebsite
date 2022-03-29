package com.pearadmin.pro.modules.sys.rest;

import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.tools.sequence.SequenceUtil;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.module.ResultTable;
import com.pearadmin.pro.common.web.domain.response.module.ResultTree;
import com.pearadmin.pro.modules.sys.domain.SysPower;
import com.pearadmin.pro.modules.sys.service.SysPowerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.pearadmin.pro.common.web.domain.response.Result.decide;

/**
 * 权限控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Api(tags = {"权限"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "power")
public class SysPowerController extends BaseController {

    @Resource
    private SysPowerService sysPowerService;

    /**
     * 查询权限
     */
    @GetMapping("data")
    @Log(title = "查询权限")
    @ApiOperation(value = "查询权限")
    public ResultTable list(SysPower sysPower){
        return treeTable(sysPowerService.list(sysPower));
    }

    /**
     * 新增权限
     *
     * @param sysPower 权限实体
     *
     * @return {@link Boolean}
     */
    @PostMapping("save")
    @Log(title = "新增权限")
    @ApiOperation(value = "新增权限")
    public Result save(@RequestBody SysPower sysPower){
        sysPower.setPowerId(SequenceUtil.makeStringId());
        return auto(sysPowerService.save(sysPower));
    }

    /**
     * 修改权限
     *
     * @param sysPower 权限实体
     *
     * @return {@link Boolean}
     */
    @PutMapping("edit")
    @Log(title = "修改权限")
    @ApiOperation(value = "修改权限")
    public Result edit(@RequestBody SysPower sysPower){
        return auto(sysPowerService.updateById(sysPower));
    }

    /**
     * 删除权限
     *
     * @param powerId 权限编号
     *
     * @return {@link Boolean}
     */
    @DeleteMapping("remove")
    @Log(title = "删除权限")
    @ApiOperation(value = "删除权限")
    public Result remove(@RequestParam String powerId){
        return auto(sysPowerService.removeById(powerId));
    }
    /**
     * Describe: 根据 Id 开启用户
     * Param powerId
     * Return ResuTree
     * */

    @PutMapping("enable")
    public Result enable(@RequestBody SysPower sysPower){
        sysPower.setEnable("1");
        boolean result = sysPowerService.update(sysPower);
        return decide(result);
    }

    /**
     * Describe: 根据 Id 禁用用户
     * Param powerId
     * Return ResuTree
     * */
    @PutMapping("disable")
    public Result disable(@RequestBody SysPower sysPower){
        sysPower.setEnable("0");
        boolean result = sysPowerService.update(sysPower);
        return decide(result);
    }
    @GetMapping("selectParent")
    public ResultTree selectParent(SysPower sysPower){
        List<SysPower> list = sysPowerService.list(sysPower);
        SysPower basePower = new SysPower();
        basePower.setPowerName("顶级权限");
        basePower.setPowerId("0");
        basePower.setParentId("-1");
        list.add(basePower);
        return dataTree(list);
    }
    @PutMapping("update")
    public Result update(@RequestBody SysPower sysPower){
        if(Strings.isBlank(sysPower.getParentId())){
            return failure("请选择上级菜单");
        }
        boolean result = sysPowerService.update(sysPower);
        return decide(result);
    }
    @DeleteMapping("removeBatch")
    @Log(title = "删除权限")
    @ApiOperation(value = "删除权限")
    public Result removeBatch(@RequestParam List<String> ids) { return auto(sysPowerService.removeByIds(ids)); }


}
