package com.pearadmin.pro.modules.sys.rest;

import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.tools.sequence.SequenceUtil;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.module.ResultTable;
import com.pearadmin.pro.common.web.domain.response.module.ResultTree;
import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.param.SysRoleGiveRequest;
import com.pearadmin.pro.modules.sys.param.SysRoleRequest;
import com.pearadmin.pro.modules.sys.service.SysDeptService;
import com.pearadmin.pro.modules.sys.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import static com.pearadmin.pro.common.web.domain.response.Result.decide;

@Slf4j
@Api(tags = {"角色"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "role")
public class SysRoleController extends BaseController {

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysDeptService sysDeptService;

    /**
     * 查询角色列表 (分页)
     *
     * @param request 查询参数
     *
     * @return {@link SysRole}
     */
    @GetMapping("page")
    @Log(title = "查询角色")
    @ApiOperation(value = "查询角色")
    public ResultTable data(SysRoleRequest request) {
        PageInfo<SysRole> pageInfo = sysRoleService.page(request);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 查询角色列表
     *
     * @param request 查询参数
     *
     * @return {@link SysRole}
     */
    @GetMapping("list")
    @Log(title = "查询角色")
    @ApiOperation(value = "查询角色")
    public Result list(SysRoleRequest request) {
        return success(sysRoleService.list(request));
    }

    /**
     * 新增角色
     *
     * @param sysRole 角色实体
     *
     * @return {@link Boolean}
     */
    @PostMapping("save")
    @Log(title = "新增角色")
    @ApiOperation(value = "新增角色")
    public Result save(@RequestBody SysRole sysRole){
        sysRole.setRoleId(SequenceUtil.makeStringId());
        sysRole.setDeleted(true);
        return auto(sysRoleService.save(sysRole));
    }

    /**
     * 修改角色
     *
     * @param sysRole 角色实体
     *
     * @return {@link Boolean}
     */
    @PutMapping("edit")
    @Log(title = "修改角色")
    @ApiOperation(value = "修改角色")
    public Result edit(@RequestBody SysRole sysRole){
        return auto(sysRoleService.updateById(sysRole));
    }

    /**
     * 删除角色
     *
     * @param roleId 角色编号
     *
     * @return {@link Boolean}
     */
    @DeleteMapping("remove")
    @Log(title = "删除角色")
    @ApiOperation(value = "删除角色")
    public Result remove(@RequestParam String roleId){
        return auto(sysRoleService.removeById(roleId));
    }

    /**
     * 删除角色
     *
     * @param ids 角色编号
     *
     * @return {@link Boolean}
     * */
    @DeleteMapping("removeBatch")
    @Log(title = "删除角色")
    @ApiOperation(value = "删除角色")
    public Result removeBatch(@RequestParam List<String> ids) { return auto(sysRoleService.removeByIds(ids)); }

    /**
     * 权限分配
     *
     * @param request 参数实体
     * */
    @PostMapping("give")
    @Log(title = "分配权限")
    @ApiOperation(value = "分配权限")
    public Result give(@RequestBody SysRoleGiveRequest request){
        return success(sysRoleService.give(request));
    }

    /**
     * 角色部门
     *
     * @param roleId 角色编号
     * */
    @GetMapping("dept")
    @Log(title = "角色部门")
    @ApiOperation(value = "角色部门")
    public Result dept(@RequestParam String roleId) { return success(sysRoleService.dept(roleId)); }

    /**
     * Describe: 根据 Id 启用角色
     * Param: roleId
     * Return: ResuBean
     * */
    @PutMapping("enable")
    @ApiOperation(value="启用角色")
    public Result enable(@RequestBody SysRole sysRole){
        sysRole.setEnable("1");
        boolean result =  sysRoleService.update(sysRole);
        return decide(result);
    }

    /**
     * Describe: 根据 Id 禁用角色
     * Param: roleId
     * Return: ResuBean
     * */
    @PutMapping("disable")
    @ApiOperation(value="禁用角色")
    public Result disable(@RequestBody SysRole sysRole){
        sysRole.setEnable("0");
        boolean result =  sysRoleService.update(sysRole);
        return decide(result);
    }
    /**
     * 角色权限
     *
     * @param roleId 角色编号
     * */
    @GetMapping("getRolePower")
    @Log(title = "角色权限")
    @ApiOperation(value = "角色权限")
    public ResultTree getRolePower(@RequestParam String roleId){
        return dataTree(sysRoleService.getRolePower(roleId));
    }
    /**
     * 部门权限
     * */
    @GetMapping("getRoleDept")
    @Log(title = "部门权限")
    @ApiOperation(value = "部门权限")
    public ResultTree getRoleDept(@RequestParam String roleId){
        return dataTree(sysRoleService.getRoleDept(roleId));
    }
}
