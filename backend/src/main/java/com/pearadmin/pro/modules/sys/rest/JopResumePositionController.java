package com.pearadmin.pro.modules.sys.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.context.UserContext;
import com.pearadmin.pro.common.tools.string.StringUtil;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.module.ResultTable;
import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.entity.JopResume;
import com.pearadmin.pro.modules.sys.entity.JopResumePosition;
import com.pearadmin.pro.modules.sys.param.JopResumePositionRequest;
import com.pearadmin.pro.modules.sys.repository.JopResumePositionRepository;
import com.pearadmin.pro.modules.sys.repository.JopResumeRepository;
import com.pearadmin.pro.modules.sys.service.JopPositionService;
import com.pearadmin.pro.modules.sys.service.JopResumePositionService;
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
@RequestMapping(ControllerConstant.PREFIX_SYS + "resume-position")
public class JopResumePositionController extends BaseController {
    @Resource
    private UserContext userContext;
    @Resource
    private JopPositionService jopPositionService;
    @Resource
    JopResumeRepository jopResumeRepository;
    @Resource
    private JopResumePositionService jopResumePositionService;
    @Resource
    private JopResumePositionRepository jopResumePositionRepository;
    @Resource
    private SysUserService sysUserService;

    @PutMapping("submitResume")
    @Log(title = "投递简历")
    @ApiOperation(value="投递简历")
    public Result submitResume(@RequestBody Map<String, String> map){
        QueryWrapper<JopResume> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userContext.getUserId());
        JopResume jopResume = jopResumeRepository.selectOne(queryWrapper);
        if (StringUtil.isEmpty(jopResume.getId())){
            return Result.failure("为创建简历无法投递");
        }
        QueryWrapper<JopResumePosition> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("resume_id", jopResume.getId());
        queryWrapper2.eq("position_id", map.get("positionId"));
        Long aLong = jopResumePositionRepository.selectCount(queryWrapper2);
        if (aLong>0){
            return Result.failure("简历无需再次投递");
        }
        JopResumePosition jopResumePosition = new JopResumePosition();
        jopResumePosition.setPositionId(map.get("positionId"));
        jopResumePosition.setResumeId(jopResume.getId());
        jopResumePosition.setCreateTime(LocalDateTime.now());
        jopResumePosition.setType("1");
        return decide(jopResumePositionService.save(jopResumePosition));
    }
    @GetMapping("submitRecords")
    @Log(title = "获取投递记录")
    @ApiOperation(value = "获取投递记录")
    public ResultTable submitRecords(JopResumePositionRequest request){
        request.setResumeUserId(userContext.getUserId());
        List<SysRole> roles = sysUserService.role(userContext.getUserId());
        Set<String> roleCodes = new HashSet<>();
        for(SysRole r:roles){
            roleCodes.add(r.getRoleCode());
        }
        if (roleCodes.contains("admin")||roleCodes.contains("manager")){
            request.setResumeUserId(null);
        }
        PageInfo<Map<String,Object>> pageInfo = jopResumePositionService.page(request);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }
    @DeleteMapping("remove")
    @Log(title = "删除申请")
    @ApiOperation(value = "删除申请")
    public Result remove(@RequestParam String id){
        return auto(jopResumePositionService.removeById(id));
    }

    @PutMapping("acceptStatus")
    @ApiOperation(value="接受状态")
    public Result disable(@RequestBody Map<String, String> json){
        System.out.println(json.toString());
        JopResumePosition jopResumePosition = new JopResumePosition();
        jopResumePosition.setAcceptStatus(json.get("acceptStatus"));
        boolean result =  jopResumePositionService.lambdaUpdate().eq(JopResumePosition::getId,json.get("id")).update(jopResumePosition);
        return decide(result);
    }
    @GetMapping("positionRecords")
    @Log(title = "获取投递记录")
    @ApiOperation(value = "获取投递记录")
    public ResultTable positionRecords(JopResumePositionRequest request){
        request.setPositionUserId(userContext.getUserId());
        List<SysRole> roles = sysUserService.role(userContext.getUserId());
        Set<String> roleCodes = new HashSet<>();
        for(SysRole r:roles){
            roleCodes.add(r.getRoleCode());
        }
        if (roleCodes.contains("admin")||roleCodes.contains("manager")){
            request.setPositionUserId(null);
        }
        PageInfo<Map<String,Object>> pageInfo = jopResumePositionService.page(request);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }
    @PutMapping("examine")
    @ApiOperation(value="接受状态")
    public Result examine(@RequestBody Map<String, String> json){
        System.out.println(json.toString());
        JopResumePosition jopResumePosition = new JopResumePosition();
        jopResumePosition.setReplyTime(LocalDateTime.now());
        jopResumePosition.setType(json.get("type"));
        if (json.get("type").equals("2")){
            jopResumePosition.setMsg(json.get("msg"));
        }
        boolean result =  jopResumePositionService.lambdaUpdate().eq(JopResumePosition::getId,json.get("id")).update(jopResumePosition);
        return decide(result);
    }
}
