package com.pearadmin.pro.modules.sys.param;

import com.pearadmin.pro.common.web.interceptor.enums.Scope;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class SysRoleGiveRequest implements Serializable {
    private String roleId;

    private List<String> powerIds = new ArrayList<>();

    private Scope scope;

    private List<String> deptIds = new ArrayList<>();

}
