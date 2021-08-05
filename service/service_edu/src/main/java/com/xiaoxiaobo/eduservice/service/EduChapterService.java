package com.xiaoxiaobo.eduservice.service;

import com.xiaoxiaobo.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxiaobo.eduservice.entity.chapter.ChapyerVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-08-02
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapyerVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String id);
}
