package com.pearadmin.pro.common.tools.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pearadmin.pro.common.constant.SystemConstant;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Servlet 工具类
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/2/3
 * */
public class ServletUtil {

    /**
     * 获取 HttpServletRequest 对象
     *
     * @return {@link HttpServletRequest}
     * */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }

    /**
     * 获取 HttpServletResponse 对象
     *
     * @return {@link HttpServletResponse}
     * */
    public static HttpServletResponse getResponse(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getResponse();
    }

    /**
     * 获取 HttpSession 对象
     *
     * @return {@link HttpSession}
     * */
    public static HttpSession getSession(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest().getSession();
    }

    /**
     * 获取 Request 请求参数
     * @param  paramName
     * @return {@link String}
     * */
    public static String getParameter(String paramName){
        return ServletUtil.getRequest().getParameter(paramName);
    }

    /**
     * 获取 Request Body 请求参数
     *
     * @return: {@link String}
     * */
    public static JSONObject getBodyParameters(){
        try {
            InputStreamReader reader = new InputStreamReader(getRequest().getInputStream(), SystemConstant.UTF8);
            char[] buff = new char[1024];
            int length = 0;
            String body = null;
            while ((length = reader.read(buff)) != -1) {
                body = new String(buff, 0, length);
            }
            return JSON.parseObject(body);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Response 对象写出数据
     *
     * @param msg 消息数据
     * */
    public static void write(String msg) throws IOException {
        HttpServletResponse response = getResponse();
        response.setHeader("Content-type","application/json;charset="+ SystemConstant.UTF8);
        response.setCharacterEncoding(SystemConstant.UTF8);
        response.getWriter().write(msg);
    }

    /**
     * Response 对象写出 JSON 数据
     *
     * @param: object 消息数据
     * */
    public static void writeJson(Object data) throws IOException{
        write(JSON.toJSONString(data));
    }

    /**
     * Request 请求参数
     *
     * @return {@link String}
     * */
    public static String getQueryParam(){
        return getRequest().getQueryString();
    }

    /**
     * Request 请求地址
     *
     * @return {@link String}
     * */
    public static String getRequestURI() {
        return getRequest().getRequestURI();
    }

    /**
     * Describe: Request 客户端地址
     *
     * @return {@link String}
     * */
    public static String getRemoteHost(){
        String remoteHost = getRequest().getRemoteHost();
        if(remoteHost.equals("0:0:0:0:0:0:0:1")){
            remoteHost = "127.0.0.1";
        }
        return remoteHost ;
    }

    /**
     * Describe: Request 客户端地址
     *
     * @return {@link String}
     * */
    public static String getIpAddress() {
        HttpServletRequest request = getRequest();
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                // 根据网卡取本机配置的IP
                try {
                    ipAddress = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        if (ipAddress != null && ipAddress.length() > 15) {
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * Request 请求方法
     *
     * @return {@link String}
     * */
    public static String getMethod(){
        return getRequest().getMethod();
    }

    /**
     * Request 请求头
     *
     * @param name 名称
     * @return {@link String}
     * */
    public static String getHeader(String name){
        return getRequest().getHeader(name);
    }

    /**
     * Request Agent
     *
     * @return {@link String}
     * */
    public static String getAgent(){
        return getHeader("User-Agent");
    }

    /**
     * Request 浏览器类型
     *
     * @return {@link String}
     * */
    public static String getBrowser(){
        String userAgent = getAgent();
        if (userAgent.contains("Firefox")) return "火狐浏览器";
        else if (userAgent.contains("Chrome")) return "谷歌浏览器";
        else if (userAgent.contains("Trident")) return "IE 浏览器";
        else return "你用啥浏览器";
    }

    /**
     * Request 访问来源 ( 客户端类型 )
     *
     * @return {@link String}
     * */
    public static String getSystem(){
        String userAgent = getAgent();
        if (getAgent().toLowerCase().contains("windows" )) return "Windows";
        else if (userAgent.toLowerCase().contains("mac" )) return "Mac";
        else if (userAgent.toLowerCase().contains("x11" )) return "Unix";
        else if (userAgent.toLowerCase().contains("android" )) return "Android";
        else if (userAgent.toLowerCase().contains("iphone" )) return "IPhone";
        else return "UnKnown, More-Info: " + userAgent;
    }
}
