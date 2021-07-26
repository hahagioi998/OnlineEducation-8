package com.xiaoxiaobo.eduservice.entity.vo;

import lombok.Data;

@Data
public class TeacherQuery {
    private String name;//名称
    private Integer level;//头衔
    private String begtn;//查询开始时间
    private String end;//查询结束时间

}
