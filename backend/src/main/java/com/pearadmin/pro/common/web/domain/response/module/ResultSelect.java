package com.pearadmin.pro.common.web.domain.response.module;

import java.io.Serializable;
import java.util.List;

/**
 * Describe: 前 端 下 拉 树
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public class ResultSelect implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 数据值字段名称
     */
    private String v;

    /**
     * 数据标题字段名称
     */
    private String n;

    /**
     * 子集数据字段名称
     */
    private List<ResultSelect> s;

    public ResultSelect()
    {
    }

    public ResultSelect(String v, String n)
    {
        this.v = v;
        this.n = n;
    }

    public List<ResultSelect> getS()
    {
        return s;
    }

    public void setN(String n)
    {
        this.n = n;
    }

    public String getN()
    {
        return n;
    }

    public void setS(List<ResultSelect> s)
    {
        this.s = s;
    }

    public String getV()
    {
        return v;
    }

    public void setV(String v)
    {
        this.v = v;
    }
}
