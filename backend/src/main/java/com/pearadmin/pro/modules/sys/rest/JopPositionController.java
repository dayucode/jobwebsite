package com.pearadmin.pro.modules.sys.rest;

import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.context.UserContext;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.module.ResultTable;
import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.entity.JopPosition;
import com.pearadmin.pro.modules.sys.param.JopPositionRequest;
import com.pearadmin.pro.modules.sys.service.JopPositionService;
import com.pearadmin.pro.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.pearadmin.pro.common.web.domain.response.Result.decide;

/**
 * 服务器监控 Api
 * */
@Api(tags = {"服务"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "position")
public class JopPositionController extends BaseController {
    @Resource
    private UserContext userContext;
    @Resource
    private JopPositionService jopPositionService;
    @Resource
    private SysUserService sysUserService;

    @GetMapping("userPosition")
    @Log(title = "查询列表")
    @ApiOperation(value = "查询列表")
    public ResultTable userPosition(JopPositionRequest request){
        request.setUserId(userContext.getUserId());
        List<SysRole> roles = sysUserService.role(userContext.getUserId());
        Set<String> roleCodes = new HashSet<>();
        for(SysRole r:roles){
            roleCodes.add(r.getRoleCode());
        }
        if (roleCodes.contains("admin")||roleCodes.contains("manager")){
            request.setUserId(null);
        }
        PageInfo<Map<String,Object>> pageInfo = jopPositionService.page(request);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }
    @GetMapping("noAuth/allPosition")
    @ApiOperation(value = "查询列表")
    public ResultTable allPosition(JopPositionRequest request){
        PageInfo<Map<String,Object>> pageInfo = jopPositionService.page(request);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }
    @GetMapping("noAuth/position")
    @ApiOperation(value = "查询列表")
    public Result position(@RequestParam String id){
        JopPositionRequest request = new JopPositionRequest();
        request.setId(id);
        Map<String,Object> map = jopPositionService.position(request);
        return success(map);
    }

    @DeleteMapping("remove")
    @Log(title = "删除职位")
    @ApiOperation(value = "删除职位")
    public Result remove(@RequestParam String id){
        return auto(jopPositionService.removeById(id));
    }

    @PutMapping("enable")
    @ApiOperation(value="启用")
    public Result enable(@RequestBody Map<String, Object> json){
        boolean result =  jopPositionService.lambdaUpdate().eq(JopPosition::getId,json.get("id")).update(new JopPosition().setEnable("0"));
        return decide(result);
    }

    @PutMapping("disable")
    @ApiOperation(value="禁用")
    public Result disable(@RequestBody Map<String, Object> json){
        boolean result =  jopPositionService.lambdaUpdate().eq(JopPosition::getId,json.get("id")).update(new JopPosition().setEnable("1"));
        return decide(result);
    }
    @PostMapping("save")
    @Log(title = "新增职位")
    @ApiOperation(value = "新增职位")
    public Result save(@RequestBody JopPosition jopPosition){
        jopPosition.setUserId(userContext.getUserId());
        jopPosition.setEnable("0");
        jopPosition.setCreateTime(LocalDateTime.now());
        return auto(jopPositionService.save(jopPosition));
    }
}
