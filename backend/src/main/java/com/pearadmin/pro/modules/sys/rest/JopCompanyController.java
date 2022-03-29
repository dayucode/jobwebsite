package com.pearadmin.pro.modules.sys.rest;

import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.context.UserContext;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.module.ResultTable;
import com.pearadmin.pro.modules.sys.entity.JopCompany;
import com.pearadmin.pro.modules.sys.param.JopCompanyRequest;
import com.pearadmin.pro.modules.sys.repository.JopCompanyRepository;
import com.pearadmin.pro.modules.sys.service.JopCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.pearadmin.pro.common.web.domain.response.Result.decide;

/**
 * 服务器监控 Api
 * */
@Api(tags = {"服务"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "company")
public class JopCompanyController extends BaseController {
    @Resource
    private JopCompanyService jopCompanyService;
    @Resource
    private JopCompanyRepository jopCompanyRepository;
    @Resource
    private UserContext userContext;

    @PostMapping("save")
    @Log(title = "新增企业")
    @ApiOperation(value = "新增企业")
    public Result save(@RequestBody JopCompany jopCompany){
        jopCompany.setUserId(userContext.getUserId());
        jopCompany.setStatus("0");
        jopCompany.setCreateTime(LocalDateTime.now());
        return auto(jopCompanyService.save(jopCompany));
    }

    @GetMapping("userCompany")
    @Log(title = "查询列表")
    @ApiOperation(value = "查询列表")
    public Result userCompany(JopCompanyRequest request) {
        request.setUserId(userContext.getUserId());
        return lay_success(jopCompanyService.list(request));
    }
    @DeleteMapping("remove")
    @Log(title = "删除企业")
    @ApiOperation(value = "删除企业")
    public Result remove(@RequestParam String id){
        return auto(jopCompanyService.removeById(id));
    }

    @GetMapping("companyList")
    @Log(title = "查询列表")
    @ApiOperation(value = "查询列表")
    public ResultTable companyList(JopCompanyRequest request) {
        request.setUserId(userContext.getUserId());
        PageInfo<Map<String,Object>> pageInfo = jopCompanyService.page(request);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }
    @PutMapping("examine")
    @ApiOperation(value="接受状态")
    public Result examine(@RequestBody Map<String, String> json){
        JopCompany jopCompany = new JopCompany();
        jopCompany.setStatus(json.get("status"));
        boolean result =  jopCompanyService.lambdaUpdate().eq(JopCompany::getId,json.get("id")).update(jopCompany);
        return decide(result);
    }
    @GetMapping("createData/{userId}")
    @ApiOperation(value="批量生成")
    public void createData(@PathVariable String userId){
        List<JopCompany> list =new ArrayList<>();
        for (int i = 0; i <200; i++) {
            JopCompany company = new JopCompany();
            company.setUserId(userId);
            company.setCompanyName("企业"+i);
            company.setBusinessNo("3e3e4r4tt5te3e3e3"+i);
            company.setLegal("法人"+i);
            if (i/2==0){
                company.setType("1");
                company.setScale("20人以下");
            }else {
                company.setType("2");
                company.setScale("1000人上");
            }
            company.setCapital(i*100);
            company.setDetailedPlace("宁夏银川市兴庆区XXX");
            company.setProvinceCode("150000");
            company.setCityCode("150500");
            company.setCountyCode("150524");
            company.setPhone("15109674440");
            company.setEmail("dayucode@foxmail.com");
            company.setStatus("1");
            company.setCreateTime(LocalDateTime.now());
            list.add(company);
        }
        jopCompanyService.saveBatch(list);
    }

}
