package com.pearadmin.pro.modules.sys.rest;

import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.context.UserContext;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.module.ResultTable;
import com.pearadmin.pro.modules.sys.entity.JopBlog;
import com.pearadmin.pro.modules.sys.param.JopBlogRequest;
import com.pearadmin.pro.modules.sys.repository.JopBlogRepository;
import com.pearadmin.pro.modules.sys.service.JopBlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;

import static com.pearadmin.pro.common.web.domain.response.Result.decide;

/**
 * 服务器监控 Api
 * */
@Api(tags = {"服务"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "blog")
public class JopBlogController extends BaseController {
    @Resource
    private JopBlogService jopBlogService;
    @Resource
    private JopBlogRepository jopBlogRepository;
    @Resource
    private UserContext userContext;

    @PostMapping("save")
    @Log(title = "新增Blog")
    @ApiOperation(value = "新增Blog")
    public Result save(@RequestBody JopBlog request){
        request.setForm(userContext.getNickName());
        request.setCreateTime(LocalDateTime.now());
        return auto(jopBlogService.save(request));
    }
    @PutMapping("update")
    @ApiOperation(value="启用")
    public Result enable(@RequestBody JopBlog request){
        boolean result =  jopBlogService.lambdaUpdate().eq(JopBlog::getId,request.getId()).update(request);
        return decide(result);
    }

    @GetMapping("list")
    @Log(title = "查询列表")
    @ApiOperation(value = "查询列表")
    public ResultTable list(JopBlogRequest request) {
        PageInfo<Map<String,Object>> pageInfo = jopBlogService.page(request);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }
    @GetMapping("listIndex")
    @ApiOperation(value = "查询列表")
    public Result listIndex() {
        JopBlogRequest request = new JopBlogRequest();
        request.setType("1");
        jopBlogService.get(request);
        return success(jopBlogService.get(request));
    }
    @GetMapping("IndexOne")
    @ApiOperation(value = "查询列表")
    public Result listIndexOne(@RequestParam String id) {
        return success(jopBlogRepository.selectById(id));
    }
    @DeleteMapping("remove")
    @Log(title = "删除企业")
    @ApiOperation(value = "删除企业")
    public Result remove(@RequestParam String id){
        return auto(jopBlogService.removeById(id));
    }

    @PutMapping("enable")
    @ApiOperation(value="启用")
    public Result enable(@RequestBody Map<String, Object> json){
        boolean result =  jopBlogService.lambdaUpdate().eq(JopBlog::getId,json.get("id")).update(new JopBlog().setEnable("0").setTime(LocalDateTime.now()));
        return decide(result);
    }

    @PutMapping("disable")
    @ApiOperation(value="禁用")
    public Result disable(@RequestBody Map<String, Object> json){
        boolean result =  jopBlogService.lambdaUpdate().eq(JopBlog::getId,json.get("id")).update(new JopBlog().setEnable("1"));
        return decide(result);
    }

    @GetMapping("get")
    @Log(title = "查询列表")
    @ApiOperation(value = "查询列表")
    public Result get(JopBlogRequest request) {
        return success(jopBlogService.get(request));
    }

}
