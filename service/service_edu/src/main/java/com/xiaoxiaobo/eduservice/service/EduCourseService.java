package com.xiaoxiaobo.eduservice.service;

import com.xiaoxiaobo.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxiaobo.eduservice.entity.chapter.ChapyerVo;
import com.xiaoxiaobo.eduservice.entity.vo.CourseInfoVo;
import com.xiaoxiaobo.eduservice.entity.vo.CoursePublishVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-08-02
 */

public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);


    CourseInfoVo getCourseInfo(String courseId);

    boolean updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getCouse(String courseId);

}
