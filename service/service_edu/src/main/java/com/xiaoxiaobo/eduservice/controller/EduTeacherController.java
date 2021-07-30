package com.xiaoxiaobo.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoxiaobo.common_util.R;
import com.xiaoxiaobo.eduservice.entity.EduTeacher;
import com.xiaoxiaobo.eduservice.entity.vo.TeacherQuery;
import com.xiaoxiaobo.eduservice.service.EduTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-20
 */
//测试
@RestController
@RequestMapping("/eduservice/teacher/")
@Slf4j
@CrossOrigin//解决跨域
public class EduTeacherController {
    //访问地址： http://localhost:8001/eduservice/teacher/findAll
    //把service注入
    @Autowired
    private EduTeacherService teacherService;

    //1 查询讲师表所有数据
    //rest风格

    @GetMapping("findAll")
    public R findAllTeacher() {
        //调用service的方法实现查询所有的操作
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items", list);
    }

    //逻辑删除讲师
    //rest风格
    @DeleteMapping("{id}")
    public R removeTeaher(@PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //3分页查询讲师
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current, @PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        teacherService.page(pageTeacher, null);
        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> record = pageTeacher.getRecords();//数据
        return R.ok().data("total", total).data("rows", record);
    }

    //分页查询带条件
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) TeacherQuery teacherquery) {
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //组合查询
        String name = teacherquery.getName();
        Integer level = teacherquery.getLevel();
        String begin = teacherquery.getBegtn();
        String end = teacherquery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified", end);
        }
        //调用方法实现条件查询分页
        teacherService.page(pageTeacher, wrapper);

        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> recods = pageTeacher.getRecords();//数据lise集合

        return R.ok().data("total", total).data("rows", recods);
    }
    //根据id搜索
    @GetMapping("find/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher=teacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }
    //修改单挑
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag=teacherService.updateById(eduTeacher);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //增加讲师
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save=teacherService.save(eduTeacher);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }
}


