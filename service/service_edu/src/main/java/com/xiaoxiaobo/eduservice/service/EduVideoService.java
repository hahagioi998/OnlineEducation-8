package com.xiaoxiaobo.eduservice.service;

import com.xiaoxiaobo.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-08-02
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeVideoByCourseId(String id);
}
