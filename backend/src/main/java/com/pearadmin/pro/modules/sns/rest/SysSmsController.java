package com.pearadmin.pro.modules.sns.rest;

import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.modules.sns.domain.SysSms;
import com.pearadmin.pro.modules.sns.service.SysSmsService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 短信控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/28
 * */
@Api(tags = {"短信"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "sms")
public class SysSmsController extends BaseController {

    @Resource
    private SysSmsService sysSmsService;

    /**
     * 发送短信
     *
     * @param sysSms 短信实体
     *
     * @return {@link Boolean}
     * */
    @PostMapping("send")
    public Result send(@RequestBody SysSms sysSms) {
        return auto(sysSmsService.send(sysSms.getPhone(), sysSms.getMessage()));
    }
}
