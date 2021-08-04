package com.xiaoxiaobo.eduservice.controller;


import com.xiaoxiaobo.common_util.R;
import com.xiaoxiaobo.eduservice.entity.EduChapter;
import com.xiaoxiaobo.eduservice.entity.EduCourseDescription;
import com.xiaoxiaobo.eduservice.entity.chapter.ChapyerVo;
import com.xiaoxiaobo.eduservice.service.EduChapterService;
import com.xiaoxiaobo.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-02
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin//解决跨域
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    //课程大纲列表
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId) {
        List<ChapyerVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo", list);
    }

    //添加章节
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return R.ok();
    }

    //根据章节id查询
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = chapterService.getById(chapterId);
        return R.ok().data("chapter", eduChapter);
    }
    //修改章节
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);
        return R.ok();
    }
    //删除章节
    @DeleteMapping("{chapterId}")
    public  R deleleteChapter(@PathVariable String chapterId){
       boolean flag= chapterService.deleteChapter(chapterId);
       if(flag){
           return R.ok();
       }else {
           return R.error();
       }

    }
}

