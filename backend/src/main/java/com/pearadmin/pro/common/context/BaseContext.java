package com.pearadmin.pro.common.context;

import com.alibaba.fastjson.JSON;
import com.pearadmin.pro.common.aop.enums.Action;
import com.pearadmin.pro.modules.sys.domain.SysLog;
import com.pearadmin.pro.common.tools.core.ServletUtil;
import com.pearadmin.pro.modules.sys.service.SysLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * Base Context
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Component
public class BaseContext {

    /**
     * 日 志 服 务
     * */
    @Resource
    private SysLogService sysLogService;

    /**
     * 用 户 服 务
     * */
    @Resource
    private UserContext userContext;

    /**
     * 新增日志
     *
     * @param title 标题
     * @param describe 描述
     * @param action 动作
     * @param state 状态
     * @param result 结果
     * @param error 异常
     * */
    @Async
    public void record(String title, String describe, Action action, Boolean state , String result, String error) {
       SysLog sysLog = new SysLog();
       sysLog.setTitle(title);
       sysLog.setAction(action);
       sysLog.setDescribe(describe);
       sysLog.setType(ServletUtil.getMethod());
       sysLog.setUrl(ServletUtil.getRequestURI());
       sysLog.setBrowser(ServletUtil.getBrowser());
       sysLog.setCreateBy(userContext.getUserId());
       sysLog.setAddress(ServletUtil.getRemoteHost());
       sysLog.setOperator(userContext.getNickName());
       sysLog.setResult(JSON.toJSONString(result));
       sysLog.setSystem(ServletUtil.getSystem());
       sysLog.setState(state);
       sysLog.setError(error);
       sysLogService.save(sysLog);
    }

}
