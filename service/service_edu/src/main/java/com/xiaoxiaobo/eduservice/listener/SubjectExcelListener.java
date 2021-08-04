package com.xiaoxiaobo.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoxiaobo.eduservice.entity.EduSubject;
import com.xiaoxiaobo.eduservice.entity.excel.SubjectData;
import com.xiaoxiaobo.eduservice.service.EduSubjectService;
import lombok.SneakyThrows;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService subjectService;

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    public SubjectExcelListener() {
    }


    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new RuntimeException("文件数据为空");
        }

        //一级分类添加
        EduSubject existOneSubkect = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (existOneSubkect == null) {
            existOneSubkect=new EduSubject();
            existOneSubkect.setParentId("0");
            existOneSubkect.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneSubkect);
        }
        String pid=existOneSubkect.getId();
        //二级分类添加
        EduSubject existTwoSubject =this.existTwoSubject(subjectService,subjectData.getTwoSubjectName(),pid);
        if(existTwoSubject==null){
            //表示没有二级分类
            existTwoSubject= new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    //判断一级标题不能重复添加
    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }
    //判断二级标题不能重复添加
    private  EduSubject existTwoSubject(EduSubjectService eduSubjectService,String TwoSubjectName,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",TwoSubjectName);
        EduSubject twoSubject =eduSubjectService.getOne(wrapper);
        return twoSubject;
    }
}
