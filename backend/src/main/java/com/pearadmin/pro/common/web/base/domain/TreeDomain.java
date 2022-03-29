package com.pearadmin.pro.common.web.base.domain;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * Base Tree 实体
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/24
 * */
@Data
public class TreeDomain<T> extends BaseDomain{

    /**
     * 父级编号
     * */
    @TableField("parent")
    private String parent;

    /**
     * 子级集合
     * */
    @TableField(exist = false)
    private List<T> children = new ArrayList<>();
}
