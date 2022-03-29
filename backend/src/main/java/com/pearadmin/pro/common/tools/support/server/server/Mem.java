package com.pearadmin.pro.common.tools.support.server.server;

import com.pearadmin.pro.common.tools.core.MathUtil;

/**
 * 内存信息
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
public class Mem
{
    /**
     * 内存总量
     */
    private double total;

    /**
     * 已用内存
     */
    private double used;

    /**
     * 剩余内存
     */
    private double free;

    public double getTotal()
    {
        return MathUtil.div(total, (1024 * 1024 * 1024), 2);
    }

    public void setTotal(long total)
    {
        this.total = total;
    }

    public double getUsed()
    {
        return MathUtil.div(used, (1024 * 1024 * 1024), 2);
    }

    public void setUsed(long used)
    {
        this.used = used;
    }

    public double getFree()
    {
        return MathUtil.div(free, (1024 * 1024 * 1024), 2);
    }

    public void setFree(long free)
    {
        this.free = free;
    }

    public double getUsage()
    {
        return MathUtil.mul(MathUtil.div(used, total, 4), 100);
    }
}
