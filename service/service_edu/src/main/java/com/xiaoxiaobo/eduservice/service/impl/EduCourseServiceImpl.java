package com.xiaoxiaobo.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoxiaobo.eduservice.entity.*;
import com.xiaoxiaobo.eduservice.entity.chapter.ChapyerVo;
import com.xiaoxiaobo.eduservice.entity.vo.CourseInfoVo;
import com.xiaoxiaobo.eduservice.entity.vo.CoursePublishVo;
import com.xiaoxiaobo.eduservice.entity.vo.CourseVo;
import com.xiaoxiaobo.eduservice.entity.vo.ReturnCourseVo;
import com.xiaoxiaobo.eduservice.mapper.EduCourseMapper;
import com.xiaoxiaobo.eduservice.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private EduTeacherService eduTeacherService;
    @Autowired
    private EduSubjectService eduSubjectService;
    @Autowired
    private  EduVideoService eduVideoService;
    @Autowired
    private  EduChapterService eduChapterService;
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

    @Override
    public Map<String,Object> getAllController(long current, long limit, CourseVo courseVo) {
        //创建page对象
        Page<EduCourse> pageController = new Page<>(current, limit);
        //组合查询
        String title = courseVo.getTitle();
        String  teacherId = courseVo.getTeacherId();
        String subjectId=courseVo.getSubjectId();
        String subjectParentId=courseVo.getSubjectParentId();
        String begtn = courseVo.getBegtn();
        String end = courseVo.getEnd();
        //构建条件
        QueryWrapper wrapper = new QueryWrapper<EduCourse>();
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(teacherId)) {
            wrapper.eq("teacher_id", teacherId);
        }
        if (!StringUtils.isEmpty(subjectId)) {
            wrapper.eq("subject_id", subjectId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            wrapper.eq("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(begtn)) {
            wrapper.ge("gmt_create", begtn);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified", end);
        }
        //调用方法实现条件查询分页
        baseMapper.selectPage (pageController,wrapper);
        long total = pageController.getTotal();//总记录数
        List<EduCourse> recods = pageController.getRecords();//数据lise集合
        //查询所有分类
        List<EduSubject> subjectslist= eduSubjectService.list(null);
        //处理所有分类
        Map<String,EduSubject> subjectHashMap=new HashMap<String, EduSubject>();
        Map<String,EduSubject> subjectParentsHashMap=new HashMap<String, EduSubject>();
        for (EduSubject eduSubject:subjectslist) {
            if(eduSubject.getParentId().equals("0")){
                subjectParentsHashMap.put(eduSubject.getId(),eduSubject);
            }else {
                subjectHashMap.put(eduSubject.getId(),eduSubject);
            }

        }
        //查询所有讲师
        List<EduTeacher> teacherlist= eduTeacherService.list(null);
        //处理所有讲师
        Map<String,EduTeacher> teacherHashMap=new HashMap<String, EduTeacher>();
        for (EduTeacher eduTeacher:teacherlist) {
            teacherHashMap.put(eduTeacher.getId(),eduTeacher);
        }
        //返回的数据
        List<ReturnCourseVo> returnCourseVos=new ArrayList<>();
        for (EduCourse eduCourse:recods) {
            ReturnCourseVo returnCourseVo=new ReturnCourseVo();
            BeanUtils.copyProperties(eduCourse,returnCourseVo);
            if(teacherHashMap.get(eduCourse.getTeacherId())!=null){
                returnCourseVo.setTeacherName(teacherHashMap.get(eduCourse.getTeacherId()).getName());
            }
            if(subjectHashMap.get(eduCourse.getSubjectId())!=null ){
                returnCourseVo.setSubjectName(subjectHashMap.get(eduCourse.getSubjectId()).getTitle());
            }
            if(subjectParentsHashMap.get(eduCourse.getSubjectParentId())!=null){
                returnCourseVo.setSubjectParentName(subjectParentsHashMap.get(eduCourse.getSubjectParentId()).getTitle());
            }
            returnCourseVos.add(returnCourseVo);
        }
            Map<String,Object> returnCourseVosMap=new HashMap<>();
        returnCourseVosMap.put("list",returnCourseVos);
        returnCourseVosMap.put("total",total);
        return returnCourseVosMap;
    }

    @Override
    @Transactional
    public void removeCourse(String id) {
        //根据课程id删除小节
        eduVideoService.removeVideoByCourseId(id);
        //根据课程id删除章节
        eduChapterService.removeChapterByCourseId(id);
        //根据课程删除描述
        courseDescriptionService.removeById(id);
        //删除课程本身
        int result=baseMapper.deleteById(id);
        if(result==0){
            throw  new RuntimeException();
        }
    }


}
