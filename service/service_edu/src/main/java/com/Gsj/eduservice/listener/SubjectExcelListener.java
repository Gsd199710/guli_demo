package com.Gsj.eduservice.listener;

import com.Gsj.eduservice.entity.EduSubject;
import com.Gsj.eduservice.entity.excel.SubjectData;
import com.Gsj.eduservice.service.EduSubjectService;
import com.Gsj.servicebase.exception.GuliException;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    //由于SubjectExcelListener不能交给spring进行管理即不能注入对象，需要自己new对象，不能
    //对数据库进行操作,这里通过构造器注入容器中
    public EduSubjectService subjectService;

    public SubjectExcelListener() {
    }
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }
    //逐行读取excel里的内容
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new GuliException(1001,"文件数据不能为空！");
        }
        //注意一级和二级分类都不能重复
        EduSubject oneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (oneSubject == null) {//没有相同的一级分类则进行添加
            oneSubject = new EduSubject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(oneSubject);
        }
        String pid = oneSubject.getId();//一级分类的id即为二级分类的parent_id
        EduSubject twoSubject = this.existTwoSubject(subjectService,
                                                    subjectData.getTwoSubjectName(),
                                                    pid);
        if (twoSubject == null) {
            twoSubject = new EduSubject();
            twoSubject.setParentId(pid);
            twoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(twoSubject);
        }
    }
    //定义判断是否重复方法
    private EduSubject existOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",0);
        EduSubject result = subjectService.getOne(wrapper);
        return result;
    }
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject result = subjectService.getOne(wrapper);
        return result;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
