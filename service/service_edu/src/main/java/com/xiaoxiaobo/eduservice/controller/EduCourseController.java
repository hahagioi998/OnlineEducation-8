package com.xiaoxiaobo.eduservice.controller;


import com.xiaoxiaobo.common_util.R;
import com.xiaoxiaobo.eduservice.entity.vo.CourseInfoVo;
import com.xiaoxiaobo.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-02
 */
@RestController
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
}

