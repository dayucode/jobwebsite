package com.pearadmin.pro.common.tools.core;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel 工具
 * */
public class ExcelUtil {

    /**
     * 读 Excel
     *
     * @param request 请求对象
     * @param clazz 对象
     * */
    public static <T> List<T> read(HttpServletRequest request, Class clazz) {
//        try {
//            return EasyExcel.read(request.getInputStream()).head(clazz).sheet().doReadSync();
//        } catch (IOException io) {
//            io.printStackTrace();
//        }
        return new ArrayList<>();
    }

    /**
     * 读 Excel
     *
     * @param filename 文件路径
     * @param clazz 对象
     * */
    public static <T> List<T> read(String filename, Class clazz) {
        //return EasyExcel.read(filename).head(clazz).sheet().doReadSync();
        return null;
    }

    /**
     * 写 Excel
     *
     * @param response 响应对象
     * @param clazz 对象
     * */
//    public static void write(HttpServletResponse response, Class clazz, List<T> list) {
//        try{
//            response.setContentType("application/vnd.ms-excel");
//            response.setCharacterEncoding(SystemConstant.UTF8);
//            EasyExcel.write(response.getOutputStream(), clazz).sheet("默认").doWrite(list);
//        }catch (IOException io) {
//            io.printStackTrace();
//        }
//    }

    /**
     * 写 Excel
     *
     * @param filename 文件名称
     * @param clazz 对象
     * */
//    public static void write(String filename, Class clazz, List<T> list) {
//        EasyExcel.write(filename, clazz).sheet("默认").doWrite(list);
//    }
}
