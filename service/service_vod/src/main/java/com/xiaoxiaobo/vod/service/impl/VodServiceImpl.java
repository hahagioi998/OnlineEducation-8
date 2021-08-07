package com.xiaoxiaobo.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.req.UploadURLStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.xiaoxiaobo.vod.service.VodService;
import com.xiaoxiaobo.vod.utIl.ConstantVodUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideoAly(MultipartFile file) {
        try {
            //fileName:原始名称
            String fileName=file.getOriginalFilename();
            //titele  上传显示名称
            String title=fileName.substring(0,fileName.lastIndexOf("."));
            //inputStream：输入留
            InputStream inputStream=file.getInputStream();

            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET, title, fileName,inputStream);
            UploadVideoImpl uploadVideo=new UploadVideoImpl();
            UploadStreamResponse response=uploadVideo.uploadStream(request);
            String videoId=null;
            if(response.isSuccess()){
                videoId=response.getVideoId();
            }else {
                videoId=response.getVideoId();
            }
            return videoId;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }
}
