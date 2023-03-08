package com.Gsj.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestExcelDemo {
    public static void main(String[] args) {
        //excel写入操作
        //设置写入文件夹地址
//        String filename = "D:\\MyDocument\\write.xlsx";
        //调用easyexcel的写入方法,write(文件路径，实体类.class)
//        EasyExcel.write(filename,DemoData.class)
//                .sheet("学生列表")
//                .doWrite(toList());

        //读取操作
        String filename = "D:\\MyDocument\\write.xlsx";
        EasyExcel.read(filename,DemoData.class,new ReadListener())
                .sheet()
                .doRead();
    }
    private static List<DemoData> toList(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i <50000 ; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("张三_"+i);
            list.add(data);
        }
        return list;
    }
}
