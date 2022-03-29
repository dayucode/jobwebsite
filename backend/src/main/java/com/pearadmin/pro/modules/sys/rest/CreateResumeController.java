package com.pearadmin.pro.modules.sys.rest;

import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.context.UserContext;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.modules.sys.service.JopResumeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import static com.pearadmin.pro.common.web.domain.response.Result.decide;

@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "resume")
public class CreateResumeController {
    @Resource
    private JopResumeService jopResumeService;
    @Resource
    private UserContext userContext;

    @PutMapping("create")
    @ApiOperation(value="创建简历")
    public Result create(){
        return decide(jopResumeService.create());
    }
    /**
     *各种文件在线预览
     */
    @GetMapping("view/{userId}")
    @ApiOperation(value="预览简历")
    public void viewResume(HttpServletResponse response, @PathVariable String userId){
        jopResumeService.viewResume(response,userId);
    }
}
