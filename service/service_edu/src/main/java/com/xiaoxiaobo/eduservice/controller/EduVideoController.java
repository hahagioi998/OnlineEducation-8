package com.xiaoxiaobo.eduservice.controller;


import com.xiaoxiaobo.common_util.R;
import com.xiaoxiaobo.eduservice.entity.EduVideo;
import com.xiaoxiaobo.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-02
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin//解决跨域
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;
    //添加小节
    @PostMapping("addVideo")
    public R  addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return R.ok();
    }
    //删除小节
    @DeleteMapping("{id}")
     public R deleteVideo(@PathVariable String id){
        eduVideoService.removeById(id);
        return R.ok();
    }
    //根据id查小节
    @GetMapping("getVideo/{VideoId}")
    public  R getVideoById(@PathVariable String VideoId){
        EduVideo eduVideo=eduVideoService.getById(VideoId);
        return R.ok().data("viideo",eduVideo);
    }
    //修改小节
    @PostMapping("updateByid")
    public R updateByid(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return R.ok();
    }
}

