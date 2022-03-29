package com.pearadmin.pro.modules.sys.param;

import com.pearadmin.pro.common.web.domain.request.PageRequest;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("JopResumePositionRequest")
public class JopResumePositionRequest extends PageRequest {
    private String id;
    private String resumeUserId;
    private String positionUserId;
    private String resumeId;
    private String positionId;
    private String companyId;
    private String companyName;
    private String title;
}
