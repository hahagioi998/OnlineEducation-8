package com.xiaoxiaobo.vodtest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.*;


import java.util.List;

public class TestVod {
//    public static void main(String[] args) throws Exception{
//        DefaultAcsClient client=InitObject.initVodClient("LTAI5tPrAaqa8MbNn8xdZxvP","KltJkhI9FFCXeFVQhqVjU7tHCTdPMC");
//        GetPlayInfoRequest request=new GetPlayInfoRequest();
//        GetPlayInfoResponse response=new GetPlayInfoResponse();
//        request.setVideoId("cb6de918d8e54b51b7c6c41a1a838bc5");
//        response=client.getAcsResponse(request);
//        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
//        //播放地址
//        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
//            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
//        }
//        //Base信息
//        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
//
//    }
    public  static void getPlayUrl()throws Exception{
        DefaultAcsClient client=InitObject.initVodClient("LTAI5tPrAaqa8MbNn8xdZxvP","KltJkhI9FFCXeFVQhqVjU7tHCTdPMC");
        GetPlayInfoRequest request=new GetPlayInfoRequest();
        GetPlayInfoResponse response=new GetPlayInfoResponse();
        request.setVideoId("cb6de918d8e54b51b7c6c41a1a838bc5");
        response=client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");

    }

    public static void main(String[] args) throws Exception{
        DefaultAcsClient client=InitObject.initVodClient("LTAI5tPrAaqa8MbNn8xdZxvP","KltJkhI9FFCXeFVQhqVjU7tHCTdPMC");
        GetVideoPlayAuthRequest request=new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response=new GetVideoPlayAuthResponse();
        request.setVideoId("cb6de918d8e54b51b7c6c41a1a838bc5");
        response=client.getAcsResponse(request);
        System.out.println("PINGZHENG:"+response.getPlayAuth());
    }
}
