package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.modules.sys.entity.JopResume;

import javax.servlet.http.HttpServletResponse;

public interface JopResumeService extends IService<JopResume> {
    boolean create();

    void viewResume(HttpServletResponse response,String userId);
}
