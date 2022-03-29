package com.pearadmin.pro.common.secure.captcha;

import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.interceptor.annotation.RateLimit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import com.pearadmin.pro.common.web.domain.response.ResultController;
import com.pearadmin.pro.common.constant.ControllerConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * Captcha Rest 服务
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@Api(tags = {"验证"})
@Slf4j
@RestController
@RequestMapping(ControllerConstant.PREFIX + "captcha")
public class SecureCaptchaRest extends ResultController {

    @Resource
    private SecureCaptchaService customCaptchaService;

    /**
     * Captcha 生成
     * */
    @RateLimit
    @GetMapping("create")
    @ApiOperation(value = "创建验证码")
    public Result createCaptcha(){
        return success(customCaptchaService.createCaptcha());
    }

}
