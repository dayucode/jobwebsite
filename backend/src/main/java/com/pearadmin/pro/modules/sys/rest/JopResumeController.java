package com.pearadmin.pro.modules.sys.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.context.UserContext;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.modules.sys.entity.JopResume;
import com.pearadmin.pro.modules.sys.repository.JopResumeRepository;
import com.pearadmin.pro.modules.sys.service.JopResumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.time.LocalDateTime;


@Api(tags = {"服务"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "resume")
public class JopResumeController extends BaseController {
    @Resource
    private UserContext userContext;
    @Resource
    JopResumeService jopResumeService;
    @Resource
    JopResumeRepository jopResumeRepository;

    @PostMapping("save")
    @Log(title = "新增简历信息")
    @ApiOperation(value = "新增简历信息")
    public Result save(@RequestBody JopResume jopResume){
        boolean result;
        jopResume.setUserId(userContext.getUserId());
        QueryWrapper<JopResume> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userContext.getUserId());
        Long count = jopResumeRepository.selectCount(queryWrapper);
        if (count<1){
            jopResume.setCreateTime(LocalDateTime.now());
            result = jopResumeService.save(jopResume);
        }else {
            jopResume.setUpdateTime(LocalDateTime.now());
            result =  jopResumeService.lambdaUpdate().eq(JopResume::getUserId,userContext.getUserId()).update(jopResume);
        }
        return auto(result);
    }
    @GetMapping("get")
    @Log(title = "查询列表")
    @ApiOperation(value = "查询列表")
    public Result get() {
        QueryWrapper<JopResume> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userContext.getUserId());
        return Result.success(jopResumeRepository.selectOne(queryWrapper));
    }

}
