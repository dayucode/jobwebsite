package com.pearadmin.pro.common.tools.system;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import lombok.Data;

import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
public class CpuInfo {

    /**
     * 磁盘相关信息
     */
    private List<SysFileInfo> sysFiles = new LinkedList<>();

    /**
     * 內存相关信息
     *
     */
    private MemInfo memInfo;

    /**
     * 核心数
     */
    private int cpuNum;

    /**
     * CPU总的使用率
     */
    private double total;

    /**
     * CPU系统使用率
     */
    private double sys;

    /**
     * CPU用户使用率
     */
    private double used;

    /**
     * CPU当前等待率
     */
    private double wait;

    /**
     * CPU当前空闲率
     */
    private double free;

    /**
     * 服务器名称
     */
    private String sysInfoComputerName;

    /**
     * 服务器Ip
     */
    private String sysInfoComputerIp;

    /**
     * 项目路径
     */
    private String sysInfoUserDir;

    /**
     * 操作系统
     */
    private String sysInfoOsName;

    /**
     * 系统架构
     */
    private String sysInfoOsArch;

   /**
     * 当前JVM占用的内存总数(M)
     */
    private double jvmInfoTotal;

    /**
     * JVM最大可用内存总数(M)
     */
    private double jvmInfoMax;

    /**
     * JVM空闲内存(M)
     */
    private double jvmInfoFree;

    /**
     * JDK版本
     */
    private String jvmInfoVersion;

    /**
     * JDK路径
     */
    private String jvmInfoHome;

    public double getJvmInfoTotal() {
        return NumberUtil.div(jvmInfoTotal, (1024 * 1024), 2);
    }

    public double getJvmInfoMax() {
        return NumberUtil.div(jvmInfoMax, (1024 * 1024), 2);
    }

    public double getJvmInfoFree() {
        return NumberUtil.div(jvmInfoFree, (1024 * 1024), 2);
    }

    public double getJvmUsed() {
        return NumberUtil.div(jvmInfoTotal - jvmInfoFree, (1024 * 1024), 2);
    }

    public double getJvmUsage() { return NumberUtil.mul(NumberUtil.div(jvmInfoTotal - jvmInfoFree, jvmInfoTotal, 4), 100); }

    /**
     * 获取JDK名称
     */
    public String getJvmInfoName() {
        return ManagementFactory.getRuntimeMXBean().getVmName();
    }

    /**
     * JDK启动时间
     */
    public String getJvmInfoStartTime() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        Date date = new Date(time);
        return DateUtil.formatDateTime(date);
    }

    /**
     * JDK运行时间
     */
    public String getJvmInfoRunTime() {

        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        Date date = new Date(time);

        long runMS = DateUtil.between(date, new Date(), DateUnit.MS);

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;

        long day = runMS / nd;
        long hour = runMS % nd / nh;
        long min = runMS % nd % nh / nm;

        return day + "天" + hour + "小时" + min + "分钟";
    }
}
