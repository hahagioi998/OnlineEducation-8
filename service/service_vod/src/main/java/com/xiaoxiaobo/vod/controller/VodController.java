package com.xiaoxiaobo.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.xiaoxiaobo.common_util.R;
import com.xiaoxiaobo.vod.service.VodService;
import com.xiaoxiaobo.vod.utIl.ConstantVodUtils;
import com.xiaoxiaobo.vod.utIl.InitObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {
    @Autowired
    private VodService vodService;
    //上传视频到阿里云
    @PostMapping("uploadAlyiVideo")
    public  R uploadAlyiVideo(MultipartFile file){
        String id=vodService.uploadVideoAly(file);
        return R.ok().data("videoId",id);
    }
    //删除阿里云视频
    @DeleteMapping("removeAlyVideo/{id}")
    public  R removeAlyVideo(@PathVariable String id){
        try {
            DefaultAcsClient defaultAcsClient= InitObject.initVodClient(ConstantVodUtils.ACCESS_KEY_ID,ConstantVodUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request=new DeleteVideoRequest();
            request.setVideoIds(id);
            defaultAcsClient.getAcsResponse(request);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("视频删除失败");
        }

    }
    }
