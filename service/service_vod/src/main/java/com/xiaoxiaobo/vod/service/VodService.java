package com.xiaoxiaobo.vod.service;

import org.springframework.web.multipart.MultipartFile;

public interface VodService {
    String uploadVideoAly(MultipartFile file);
}
