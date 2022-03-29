package com.pearadmin.pro.modules.sys.rest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.context.UserContext;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.module.ResultTable;
import com.pearadmin.pro.modules.sys.domain.SysLog;
import com.pearadmin.pro.modules.sys.domain.SysMenu;
import com.pearadmin.pro.modules.sys.domain.SysUser;
import com.pearadmin.pro.modules.sys.entity.JopCompany;
import com.pearadmin.pro.modules.sys.entity.JopPosition;
import com.pearadmin.pro.modules.sys.entity.JopResumePosition;
import com.pearadmin.pro.modules.sys.param.SysUserPasswordRequest;
import com.pearadmin.pro.modules.sys.param.SysUserRequest;
import com.pearadmin.pro.modules.sys.param.SysUserRoleRequest;
import com.pearadmin.pro.modules.sys.repository.JopCompanyRepository;
import com.pearadmin.pro.modules.sys.repository.JopPositionRepository;
import com.pearadmin.pro.modules.sys.repository.JopResumePositionRepository;
import com.pearadmin.pro.modules.sys.repository.SysLogRepository;
import com.pearadmin.pro.modules.sys.service.SysUserService;
import com.pearadmin.pro.modules.sys.util.CalendarUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

import static com.pearadmin.pro.common.web.domain.response.Result.decide;

/**
 * 用户控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/28
 * */
@Slf4j
@Api(tags = {"用户"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "user")
public class SysUserController extends BaseController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private UserContext userContext;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private SysLogRepository sysLogRepository;
    @Resource
    private JopPositionRepository jopPositionRepository;
    @Resource
    private JopCompanyRepository jopCompanyRepository;
    @Resource
    private JopResumePositionRepository jopResumePositionRepository;



    /**
     * 新增用户
     *
     * @param sysUser 用户实体
     */
    @PostMapping("save")
    @Log(title = "用户新增")
    @ApiOperation(value = "用户新增")
    public Result save(@RequestBody SysUser sysUser){
        if(sysUserService.exist(sysUser.getUsername())) {
            return Result.failure("账号已存在");
        }
        String password = sysUser.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        sysUser.setPassword(encodePassword);
        sysUser.setLocked(false);
        sysUser.setEnable("1");
        return auto(sysUserService.save(sysUser));
    }

    /**
     * 用户注册
     * @param sysUser 注册实体
     */
    @PostMapping("register")
    @ApiOperation(value = "用户注册")
    public Result register(SysUser sysUser){
        return sysUserService.register(sysUser);
    }

    /**
     * 修改用户
     * @param sysUser 用户实体
     */
    @PutMapping("edit")
    @Log(title = "用户修改")
    @ApiOperation(value = "用户修改")
    public Result edit(@RequestBody SysUser sysUser){
        return auto(sysUserService.update(sysUser));
    }

    /**
     * 修改密码
     *
     * @param request 参数实体
     * */
    @PutMapping("password/edit")
    @Log(title = "修改密码")
    @ApiOperation(value = "修改密码")
    public Result editPassword(@RequestBody SysUserPasswordRequest request) {
        String userId = userContext.getUserId();
        String newPassword = request.getNewPassword();
        String confirmPassword = request.getConfirmPassword();
        String oldPassword = request.getOldPassword();
        if(!newPassword.equals(confirmPassword)) {
            return failure("两次输入密码不一致");
        }
        if(!sysUserService.contrastPassword(userId,oldPassword)) {
            return failure("密码不正确");
        }
        return auto(sysUserService.editPassword(userId,newPassword));
    }

    /**
     * 重置密码
     *
     * @param
     * */
    @PutMapping("password/reset")
    @Log(title = "重置密码")
    @ApiOperation(value = "重置密码")
    public Result resetPassword(String id) {
        return auto(sysUserService.resetPassword(id));
    }

    /**
     * 查询用户
     *
     * @param request 查询参数
     */
    @GetMapping("page")
    @Log(title = "用户列表")
    @ApiOperation(value = "用户列表")
    public ResultTable page(SysUserRequest request){
        PageInfo<SysUser> pageInfo = sysUserService.page(request);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 查询用户
     *
     * @param request 查询参数
     */
    @GetMapping("list")
    @Log(title = "用户列表")
    @ApiOperation(value = "用户列表")
    public Result list(SysUserRequest request){ return success(sysUserService.list(request)); }

    /**
     * 分配角色
     *
     * @param request 参数实体
     * */
    @PostMapping("give")
    @Log(title = "分配角色")
    @ApiOperation(value = "分配角色")
    public Result give(@RequestBody SysUserRoleRequest request){
        return success(sysUserService.give(request.getUserId(),request.getRoleIds()));
    }

    /**
     * 删除用户
     *
     * @param id 用户编号
     */
    @DeleteMapping("remove")
    @Log(title = "用户删除")
    @ApiOperation(value = "用户删除")
    public Result remove(@RequestParam String id){
        return auto(sysUserService.removeById(id));
    }

    /**
     * 批量删除
     *
     * @param ids 用户编号
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam String ids) {
        return auto(sysUserService.removeByIds(Arrays.asList(ids.split(","))));
    }

    /**
     * 个人资料
     */
    @GetMapping("profile")
    @Log(title = "个人资料")
    @ApiOperation(value = "个人资料")
    public Result profile(){
        return success(sysUserService.getById(userContext.getUserId()));
    }

    /**
     * 用户详情
     *
     * @param userId 用户编号
     * */
    @GetMapping("info")
    @Log(title = "用户详情")
    @ApiOperation(value = "用户详情")
    public Result info(String userId){
        return success(sysUserService.getById(userId));
    }

    /**
     * 获取用户菜单
     *
     * @return {@link Result}
     * */
    @GetMapping("menu")
    @ApiOperation(value = "用户菜单")
    public List<SysMenu> menu(){
        return sysUserService.menu(userContext.getUserId());
    }

    /**
     * 获取用户角色
     *
     * @param userId 用户编号
     * */
    @GetMapping("role")
    @ApiOperation(value = "用户角色")
    public Result role(String userId){
        return success(sysUserService.role(userId));
    }

    /**
     * 获取用户角色
     *
     * @param userId 用户编号
     * */
    @GetMapping("userRole")
    @ApiOperation(value = "用户角色")
    public ResultTable userRole(@RequestParam String userId){
        return dataTable(sysUserService.getUserRole(userId));
    }

    /**
     * 获取用户权限
     * */
    @GetMapping("power")
    @ApiOperation(value = "用户权限")
    public Result power(){
        return success(sysUserService.power(userContext.getUserId()));
    }

    /**
     * 获取用户部门
     * */
    @GetMapping("dept")
    @ApiOperation(value = "用户部门")
    public Result dept() {
        return success();
    }

    /**
     * Describe: 根据 userId 开启用户
     * Param: SysUser
     * Return: 执行结果
     */
    @PutMapping("enable")
    @ApiOperation(value = "开启用户登录")
    public Result enable(@RequestBody SysUser sysUser) {
        sysUser.setEnable("1");
        boolean result = sysUserService.update(sysUser);
        return decide(result);
    }
    /**
     * Describe: 根据 userId 禁用用户
     * Param: SysUser
     * Return: 执行结果
     */
    @PutMapping("disable")
    @ApiOperation(value = "禁用用户登录")
    public Result disable(@RequestBody SysUser sysUser) {
        sysUser.setEnable("0");
        boolean result = sysUserService.update(sysUser);
        return decide(result);
    }

    /**
     * 获取发布页面路径
     */
    @GetMapping("enterAdmin")
    @ApiOperation(value = "获取页面")
    public Result enterAdmin(){
        return success("index.html");
    }
    /**
     * 获取发布页面路径
     */
    @GetMapping("enterPerson")
    @Log(title = "获取页面")
    @ApiOperation(value = "获取页面")
    public Result enterPerson(){
        return success("view/system/user/person.html");
    }

    @GetMapping("adminIndexData")
    @ApiOperation(value = "用户权限")
    public Result adminIndexData(){
        Calendar calendar = CalendarUtil.getIntervalNow(Calendar.HOUR_OF_DAY, 1);
        LambdaQueryWrapper<SysLog> queryWrapper1 = new LambdaQueryWrapper<SysLog>().gt(SysLog::getCreateTime, calendar.getTime());
        Long visitCount = sysLogRepository.selectCount(queryWrapper1);
        LambdaQueryWrapper<JopPosition> queryWrapper2 = new LambdaQueryWrapper<JopPosition>();
        Long positionCount = jopPositionRepository.selectCount(queryWrapper2);
        LambdaQueryWrapper<JopCompany> queryWrapper3 = new LambdaQueryWrapper<JopCompany>();
        Long companyCount = jopCompanyRepository.selectCount(queryWrapper3);
        LambdaQueryWrapper<JopResumePosition> queryWrapper4 = new LambdaQueryWrapper<JopResumePosition>();
        Long resumePositionCount = jopResumePositionRepository.selectCount(queryWrapper4);
        Map<String,Object> map = new HashMap<>();
        map.put("visitCount",visitCount);
        map.put("positionCount",positionCount);
        map.put("companyCount",companyCount);
        map.put("resumePositionCount",resumePositionCount);
        return success(map);
    }


}