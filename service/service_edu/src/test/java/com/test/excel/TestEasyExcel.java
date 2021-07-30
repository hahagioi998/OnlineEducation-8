package com.test.excel;

import com.alibaba.excel.EasyExcel;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) {
        //文件名
        String filename="C:\\Users\\gtb12\\Desktop\\write.xlsx";
     //   EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());
        EasyExcel.read(filename,DemoData.class,new ExcelListtener()).sheet().doRead();
    }
    private static List<DemoData> getData(){
        List<DemoData> list =new ArrayList<>();
        for (int i=0;i<10;i++){
            DemoData data=new DemoData();
            data.setSno(i);
            data.setSname("lucy"+i);
            list.add(data);
        }
        return list;
    }
}
