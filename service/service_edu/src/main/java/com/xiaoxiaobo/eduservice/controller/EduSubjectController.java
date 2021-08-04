package com.xiaoxiaobo.eduservice.controller;


import com.xiaoxiaobo.common_util.R;
import com.xiaoxiaobo.eduservice.entity.subject.OneSubject;
import com.xiaoxiaobo.eduservice.entity.subject.TwoSubject;
import com.xiaoxiaobo.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-30
 */
@RestController
@RequestMapping("/eduservice/subject/")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    //添加课程分类
    //获取到上传来的文件
    @PostMapping("addSubkect")
    public R addSubject(MultipartFile file) {
        subjectService.saveSubject(file, subjectService);
        return R.ok();
    }
    //查询所有课程
    @GetMapping("getAllSubject")
    public R getAllSubject() {
       List<OneSubject> list=subjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }
    //根据一级分类查询所有二级分
}


