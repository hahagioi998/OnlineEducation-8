package com.xiaoxiaobo.eduservice.service;

import com.xiaoxiaobo.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxiaobo.eduservice.entity.chapter.ChapyerVo;
import com.xiaoxiaobo.eduservice.entity.vo.CourseInfoVo;
import com.xiaoxiaobo.eduservice.entity.vo.CoursePublishVo;
import com.xiaoxiaobo.eduservice.entity.vo.CourseVo;
import com.xiaoxiaobo.eduservice.entity.vo.ReturnCourseVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    Map<String,Object> getAllController(long current, long limit, CourseVo courseVo);

    void removeCourse(String id);
}
