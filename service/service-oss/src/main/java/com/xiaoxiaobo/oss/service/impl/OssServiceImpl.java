package com.xiaoxiaobo.oss.service.impl;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.xiaoxiaobo.oss.service.OssService;
import com.xiaoxiaobo.oss.util.ConstantPropertiesUtils;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //获取输入留
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String filename=file.getOriginalFilename();
            String UUID= java.util.UUID.randomUUID().toString().replaceAll("-","");
            //给唯一值
            filename=UUID+filename;
            //给日期
            String datePath=new DateTime().toString("yyy/MM/dd");
            filename=datePath+"/"+filename;

            ossClient.putObject(bucketName, filename, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //返回阿里云路径
            String url="https://"+bucketName+"."+endpoint+"/"+filename;
            return  url;
        } catch (Exception e) {
            e.printStackTrace();;
            return null;
        }

    }
}
