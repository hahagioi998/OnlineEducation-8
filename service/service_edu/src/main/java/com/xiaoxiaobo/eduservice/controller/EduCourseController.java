package com.xiaoxiaobo.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoxiaobo.common_util.R;
import com.xiaoxiaobo.eduservice.entity.EduCourse;
import com.xiaoxiaobo.eduservice.entity.EduTeacher;
import com.xiaoxiaobo.eduservice.entity.vo.*;
import com.xiaoxiaobo.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-02
 */
@RestController
@Controller
@RequestMapping("/eduservice/edu_course")
@CrossOrigin//解决跨域
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    //添加课程基本信息
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }

    //查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo", courseInfoVo);
    }

    //修改课程基本信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        boolean result = eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok().data("result",result);
    }
    //查询课程以及相关信息
    @GetMapping("getCouse/{courseId}")
    public R getCouse(@PathVariable String courseId){
        CoursePublishVo coursePublishVo=eduCourseService.getCouse(courseId);
        return R.ok().data("coursePublishVo",coursePublishVo);
    }
    //课程最后发布
    @GetMapping("publishCourse/{courseId}")
    public  R publishCourse(@PathVariable String courseId){
        EduCourse eduCourse=new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }

    //分页查询带条件
    @PostMapping("getAllCourse/{current}/{limit}")
    public R addAllController(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) CourseVo courseVo) {
        Map<String,Object> list=eduCourseService.getAllController(current,limit,courseVo);
        return R.ok().data("addAllController",list);
    }
    //删除课程
    @DeleteMapping("deleteCourse/{id}")
    public R deleteCourse(@PathVariable String id){
        eduCourseService.removeCourse(id);
        return R.ok();
    }

}

