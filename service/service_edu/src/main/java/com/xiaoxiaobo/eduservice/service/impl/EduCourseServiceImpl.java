package com.xiaoxiaobo.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.xiaoxiaobo.eduservice.entity.EduChapter;
import com.xiaoxiaobo.eduservice.entity.EduCourse;
import com.xiaoxiaobo.eduservice.entity.EduCourseDescription;
import com.xiaoxiaobo.eduservice.entity.chapter.ChapyerVo;
import com.xiaoxiaobo.eduservice.entity.vo.CourseInfoVo;
import com.xiaoxiaobo.eduservice.entity.vo.CoursePublishVo;
import com.xiaoxiaobo.eduservice.mapper.EduCourseMapper;
import com.xiaoxiaobo.eduservice.service.EduCourseDescriptionService;
import com.xiaoxiaobo.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-08-02
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1.向课程表添加课程基本信息
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert=baseMapper.insert(eduCourse);
//        if(insert<=0){
//            throw new RuntimeException();
//        }
        String Cid=eduCourse.getId();
        //2 向课程简介表添加课程简介
        EduCourseDescription EduCourseDescription=new EduCourseDescription();
        EduCourseDescription.setDescription(courseInfoVo.getDescription());
        EduCourseDescription.setId(Cid);
        courseDescriptionService.save(EduCourseDescription);
        return Cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        EduCourse eduCourse=baseMapper.selectById(courseId);
        EduCourseDescription courseDescription=courseDescriptionService.getById(courseId);
        CourseInfoVo courseInfoVo=new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        BeanUtils.copyProperties(courseDescription,courseInfoVo);
        return courseInfoVo;
    }

    @Override
    public boolean updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        EduCourseDescription eduCourseDescription=new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,eduCourseDescription);
        int update=baseMapper.updateById(eduCourse);
        if(update==0){
            throw new RuntimeException("修改课程信息失败");
        }
        boolean returns=courseDescriptionService.updateById(eduCourseDescription);
        return returns;
    }

    @Override
    public CoursePublishVo getCouse(String courseId) {
        CoursePublishVo coursePublishVo= baseMapper.selectCoursePublishVoById(courseId);
        return coursePublishVo;
    }




}
