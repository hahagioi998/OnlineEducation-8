package com.xiaoxiaobo.oss.controller;

import com.xiaoxiaobo.common_util.R;
import com.xiaoxiaobo.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssConteoller {
    @Autowired
    private OssService ossService;
    //上传头像方法
    @PostMapping
    public R uploadOssFile(MultipartFile file){
        String url=ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
