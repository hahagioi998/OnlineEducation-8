package com.xiaoxiaobo.vod.utIl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantVodUtils implements InitializingBean {

    @Value("${aliyun.oss.file.keyid}")
    private String keyID;
    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;



    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;


    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID=keyID;
        ACCESS_KEY_SECRET=keySecret;
    }
}
