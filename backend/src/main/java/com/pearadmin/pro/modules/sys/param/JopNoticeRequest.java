package com.pearadmin.pro.modules.sys.param;

import com.pearadmin.pro.common.web.domain.request.PageRequest;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class JopNoticeRequest extends PageRequest {
    private String id;
    private String avatar;
    private String title;
    private String context;
    private String form;
    private LocalDateTime time;
    private String type;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
