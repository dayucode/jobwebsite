package com.pearadmin.pro.modules.not.param;

import com.pearadmin.pro.common.web.domain.request.PageRequest;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 私信列表 -- 参数实体
 * <p>
 * author: 就眠仪式
 * date: 2021-04-01
 */
@Data
@Alias("SysInboxRequest")
public class SysInboxRequest extends PageRequest {

    private String content;
}
