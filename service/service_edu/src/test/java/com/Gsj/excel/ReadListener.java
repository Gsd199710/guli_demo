package com.Gsj.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class ReadListener extends AnalysisEventListener<DemoData> {
    //除表头外逐行读取excel内容
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println("*****"+demoData);
    }
    //读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头:"+headMap);
    }

    //读取完成之后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
