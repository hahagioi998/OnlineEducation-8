package com.xiaoxiaobo.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.xiaoxiaobo.eduservice.entity.EduSubject;
import com.xiaoxiaobo.eduservice.entity.excel.SubjectData;
import com.xiaoxiaobo.eduservice.entity.subject.OneSubject;
import com.xiaoxiaobo.eduservice.entity.subject.TwoSubject;
import com.xiaoxiaobo.eduservice.listener.SubjectExcelListener;
import com.xiaoxiaobo.eduservice.mapper.EduSubjectMapper;
import com.xiaoxiaobo.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-07-30
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            InputStream in=file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //查询所有一级分类
        QueryWrapper<EduSubject> wrapperOne=new QueryWrapper<>();
        wrapperOne.eq("parent_id",0);
        List<EduSubject> eduOneSubjectList=baseMapper.selectList(wrapperOne);
        //查询所有二级分为
        QueryWrapper<EduSubject> wrapperTwo=new QueryWrapper<>();
        wrapperTwo.ne("parent_id",0);
        List<EduSubject> eduTwoSubjectList=baseMapper.selectList(wrapperTwo);
        //创建list集合，用于最终的封装
        List<OneSubject> finalSubjectList=new ArrayList<>();

        for (EduSubject eduSubject:eduOneSubjectList) {
            OneSubject oneSubject=new OneSubject();
//            oneSubject.setId(eduSubject.getId());
//            oneSubject.setTitle(eduSubject.getTitle());
            BeanUtils.copyProperties(eduSubject,oneSubject);
            for (EduSubject eduSubject2:eduTwoSubjectList) {
                TwoSubject twoSubject=new TwoSubject();
                if(eduSubject2.getParentId().equals(eduSubject.getId())){
                    BeanUtils.copyProperties(eduSubject2,twoSubject);
//                    twoSubject.setId(eduSubject2.getId());
//                    twoSubject.setTitle(eduSubject2.getTitle());
                   oneSubject.getChildren().add(twoSubject);
                }
            }
            finalSubjectList.add(oneSubject);
        }
        return finalSubjectList;
    }
}
