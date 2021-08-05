package com.xiaoxiaobo.eduservice.entity.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CourseVo implements Serializable {

    private String title;

    private String teacherId;

    private String subjectId;

    private String subjectParentId;
    private String begtn;//查询开始时间
    private String end;//查询结束时间
}
