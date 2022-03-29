package com.pearadmin.pro.modules.sys.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe: 前端菜单数据封装信息
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
@Accessors(chain = true)
public class SysMenu {

    /**
     * 菜单编号
     * */
    private String id;

    /**
     * 父节点
     * */
    private String parentId;

    /**
     * 标题
     * */
    private String title;

    /**
     * 菜单类型
     * */
    private String type;

    /**
     * 打 开 类 型
     * */
    private String openType;

    /**
     * 图标
     * */
    private String icon;

    /**
     * 跳转路径
     * */
    private String href;

    /**
     * 子菜单
     * */
    private List<SysMenu> children = new ArrayList<>();
    /**
     * 权限标识
     */
    private String powerCode;
}
