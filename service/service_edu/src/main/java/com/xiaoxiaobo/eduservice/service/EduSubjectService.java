package com.xiaoxiaobo.eduservice.service;

import com.xiaoxiaobo.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxiaobo.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-07-30
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService subjectService);

    List<OneSubject> getAllOneTwoSubject();
}
