package com.pearadmin.pro.modules.sys.rest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.context.UserContext;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.module.ResultTable;
import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.entity.JopNotice;
import com.pearadmin.pro.modules.sys.param.JopNoticeRequest;
import com.pearadmin.pro.modules.sys.repository.JopNoticeRepository;
import com.pearadmin.pro.modules.sys.service.JopNoticeService;
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
@RequestMapping(ControllerConstant.PREFIX_SYS + "notice")
public class JopNoticeController extends BaseController {
    @Resource
    private JopNoticeRepository jopNoticeRepository;
    @Resource
    private JopNoticeService jopNoticeService;
    @Resource
    private UserContext userContext;
    @Resource
    private SysUserService sysUserService;

    @PostMapping("save")
    @Log(title = "新增咨询")
    @ApiOperation(value = "新增咨询")
    public Result save(@RequestBody JopNotice jopNotice){
        jopNotice.setForm(userContext.getNickName());
        jopNotice.setCreateTime(LocalDateTime.now());
        return auto(jopNoticeService.save(jopNotice));
    }
    @PutMapping("update")
    @ApiOperation(value="启用")
    public Result enable(@RequestBody JopNotice jopNotice){
        boolean result =  jopNoticeService.lambdaUpdate().eq(JopNotice::getId,jopNotice.getId()).update(jopNotice);
        return decide(result);
    }

    @GetMapping("list")
    @Log(title = "查询列表")
    @ApiOperation(value = "查询列表")
    public ResultTable list(JopNoticeRequest request) {
        PageInfo<Map<String,Object>> pageInfo = jopNoticeService.page(request);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }
    @DeleteMapping("remove")
    @Log(title = "删除企业")
    @ApiOperation(value = "删除企业")
    public Result remove(@RequestParam String id){
        return auto(jopNoticeService.removeById(id));
    }

    @PutMapping("enable")
    @ApiOperation(value="启用")
    public Result enable(@RequestBody Map<String, Object> json){
        boolean result =  jopNoticeService.lambdaUpdate().eq(JopNotice::getId,json.get("id")).update(new JopNotice().setEnable("0").setTime(LocalDateTime.now()));
        return decide(result);
    }

    @PutMapping("disable")
    @ApiOperation(value="禁用")
    public Result disable(@RequestBody Map<String, Object> json){
        boolean result =  jopNoticeService.lambdaUpdate().eq(JopNotice::getId,json.get("id")).update(new JopNotice().setEnable("1"));
        return decide(result);
    }

    @GetMapping("get")
    @Log(title = "查询列表")
    @ApiOperation(value = "查询列表")
    public Result get() {
        JopNoticeRequest request = new JopNoticeRequest();
        List<SysRole> roles = sysUserService.role(userContext.getUserId());
        Set<String> roleCodes = new HashSet<>();
        for(SysRole r:roles){
            roleCodes.add(r.getRoleCode());
        }
        if (roleCodes.contains("admin")||roleCodes.contains("manager")){
            request.setType("1");
        }else if(roleCodes.contains("user")){
            request.setType("2");
        }else {
            request.setType("3");
        }
        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();
        json.put("id","1");
        json.put("title","公告");
        json.put("children",jopNoticeService.get(request));
        jsonArray.add(json);
        return success(jsonArray);
    }
}
