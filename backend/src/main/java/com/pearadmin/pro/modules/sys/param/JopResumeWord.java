package com.pearadmin.pro.modules.sys.param;

import com.deepoove.poi.data.PictureRenderData;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JopResumeWord {
    private String id;
    private String userId;
    private String birth;
    private String addr;
    private String apply;
    private Integer highSalary;
    private Integer lowSalary;
    private String expectWorkPlace;
    private String graduationTime;
    private String education;
    private String school;
    private String startWorkTime;
    private String educationalExperience;
    private String trainingExperience;
    private String workExperience;
    private String skill;
    private String name;
    private PictureRenderData portrait;
    private String phone;
    private String sex;
    private String origin;
    private String profession;
    private String email;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
