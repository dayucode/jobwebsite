package com.pearadmin.pro.modules.not.param;

import com.pearadmin.pro.common.web.domain.request.PageRequest;
import lombok.Data;

/**
 * 公告列表 -- 参数实体
 * <p>
 * author: 就眠仪式
 * date: 2021-04-01
 */
@Data
public class SysAnnounceUserRequest extends PageRequest {

    /**
     * 公告标题
     * */
    private String title;

}
