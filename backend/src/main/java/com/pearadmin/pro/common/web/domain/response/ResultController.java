package com.pearadmin.pro.common.web.domain.response;

import com.pearadmin.pro.common.web.domain.response.module.ResultTable;
import com.pearadmin.pro.common.web.domain.response.module.ResultTree;

/**
 * Ajax 响 应 处 理
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
public class ResultController {

    /**
     * 成功操作
     * */
    public Result success(){
        return Result.success();
    }

    /**
     * 成功操作
     * */
    public Result success(String msg){
        return Result.success(msg);
    }

    /**
     * 成功操作
     * */
    public Result success(Object data){
        return Result.success(data);
    }
    /**
     * 成功操作
     * */
    public Result lay_success(Object data){
        return Result.lay_success(data);
    }

    /**
     * 成功操作
     * */
    public Result success(String msg,Object data){
        return Result.success(msg,data);
    }

    /**
     * 成功操作
     * */
    public Result success(int code,String message,Object data){
        return Result.success(code,message,data);
    }

    /**
     * 失败操作
     * */
    public Result failure(){
        return Result.failure();
    }

    /**
     * 失败操作
     * */
    public Result failure(String msg){
        return Result.failure(msg);
    }

    /**
     * 失败操作
     * */
    public Result failure(String msg,Object data){
        return Result.failure(msg,data);
    }

    /**
     * 失败操作
     * */
    public Result failure(int code,String msg,Object data){
        return Result.failure(code,msg,data);
    }

    /**
     * 选择返回
     * */
    public Result auto(Boolean b){
        return Result.auto(b);
    }

    /**
     * 选择返回
     * */
    public Result auto(Boolean b,String success,String failure){
        return Result.auto(b,success,failure);
    }

    /**
     * Describe: 返回 Tree 数据
     * Param data
     * Return Tree数据
     * */
    protected  static ResultTree dataTree(Object data){
        ResultTree resuTree = new ResultTree();
        resuTree.setData(data);
        return resuTree;
    }

    /**
     * Describe: 返回数据表格数据 分页
     * Param data
     * Return 表格分页数据
     * */
    protected  static ResultTable pageTable(Object data, long count){
        return ResultTable.pageTable(count,data);
    }

    /**
     * Describe: 返回数据表格数据
     * Param data
     * Return 表格分页数据
     * */
    protected  static ResultTable dataTable(Object data){
        return ResultTable.dataTable(data);
    }

    /**
     * Describe: 返回树状表格数据 分页
     * Param data
     * Return 表格分页数据
     * */
    protected  static ResultTable treeTable(Object data){
        return ResultTable.dataTable(data);
    }

}
