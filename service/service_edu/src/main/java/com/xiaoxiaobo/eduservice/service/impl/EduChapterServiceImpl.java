package com.xiaoxiaobo.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoxiaobo.eduservice.entity.EduChapter;
import com.xiaoxiaobo.eduservice.entity.EduCourse;
import com.xiaoxiaobo.eduservice.entity.EduVideo;
import com.xiaoxiaobo.eduservice.entity.chapter.ChapyerVo;
import com.xiaoxiaobo.eduservice.entity.chapter.VideoVo;
import com.xiaoxiaobo.eduservice.mapper.EduChapterMapper;
import com.xiaoxiaobo.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxiaobo.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService;
    @Override
    public List<ChapyerVo> getChapterVideoByCourseId(String courseId) {
        //根据课程id查询所有的章节
        QueryWrapper<EduChapter> wrapperChapter=new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChapterList=baseMapper.selectList(wrapperChapter);
        //根据章节查到小节
        QueryWrapper<EduVideo> wrapperVideo=new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduVideo> eduVideoList=eduVideoService.list(wrapperVideo);
        List<ChapyerVo> list=new ArrayList<>();
        for (EduChapter eduChapter:eduChapterList) {
            ChapyerVo chapyerVo=new ChapyerVo();
            BeanUtils.copyProperties(eduChapter,chapyerVo);
            for (EduVideo eduVideo:eduVideoList) {
                if(eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo=new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    chapyerVo.getChildren().add(videoVo);
                }
            }
            list.add(chapyerVo);
        }
        return list;
    }
//删除章节方法
    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> wrapper=new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count=eduVideoService.count(wrapper);
        if(count>0){
            throw  new RuntimeException("不能删除");
        }else {
//删除章节
            int result=baseMapper.deleteById(chapterId);
            return result>0;
        }

    }

    @Override
    public void removeChapterByCourseId(String id) {
        QueryWrapper<EduChapter> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }
}
