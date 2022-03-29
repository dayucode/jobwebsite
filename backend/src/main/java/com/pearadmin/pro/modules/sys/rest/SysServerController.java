package com.pearadmin.pro.modules.sys.rest;

import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.tools.support.server.Server;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器监控 Api
 * */
@Api(tags = {"服务"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "server")
public class SysServerController extends BaseController {

    /**
     * 监控详情
     *
     * @return {@link Server}
     * */
    @GetMapping("info")
    @Log(title = "服务详情")
    @ApiOperation(value = "服务详情")
    public Result server() throws Exception{
        Server server = new Server();
        server.copyTo();
        return success(server);
    }
}
