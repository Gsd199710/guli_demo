package com.Gsj.eduservice.service.impl;

import com.Gsj.eduservice.entity.EduSubject;
import com.Gsj.eduservice.entity.excel.SubjectData;
import com.Gsj.eduservice.entity.subject.OneSubject;
import com.Gsj.eduservice.entity.subject.TwoSubject;
import com.Gsj.eduservice.listener.SubjectExcelListener;
import com.Gsj.eduservice.mapper.EduSubjectMapper;
import com.Gsj.eduservice.service.EduSubjectService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author test.java
 * @since 2023-03-07
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {

        try {
            //文件输入流
            InputStream ins = file.getInputStream();
            //调用方法读取
            EasyExcel.read(ins, SubjectData.class,new SubjectExcelListener(subjectService))
                    .sheet()
                    .doRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OneSubject> getAllSubjects() {
        //查询所有的一级、二级分类
        QueryWrapper<EduSubject> onewrapper = new QueryWrapper<>();
        onewrapper.eq("parent_id","0");
        //baseMapper是ServiceImpl自动注入的
        List<EduSubject> oneSubjectList = baseMapper.selectList(onewrapper);

        QueryWrapper<EduSubject> twowrapper = new QueryWrapper<>();
        twowrapper.ne("parent_id","0");
        //baseMapper是ServiceImpl自动注入的
        List<EduSubject> twoSubjectList = baseMapper.selectList(twowrapper);
        //对分类列表封装
        List<OneSubject> finalList = oneSubjectList.stream().map(e ->{
            OneSubject oneSubject = new OneSubject();
            oneSubject.setId(e.getId());
            oneSubject.setTitle(e.getTitle());
            //二级列表
            List<TwoSubject> twoFinalList = twoSubjectList.stream().map(s ->{
                //判断指定的二级分类是否在一级分类目录下
                if (s.getParentId().equals(e.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    twoSubject.setId(s.getId());
                    twoSubject.setTitle(s.getTitle());
                    return twoSubject;
                }
                return null;
            }).collect(Collectors.toList());
            //将二级分类加入到一级分类目录下
            if (twoFinalList.size() != 0){
                oneSubject.setChildren(twoFinalList);
            }
            return oneSubject;
        }).collect(Collectors.toList());

        return finalList;
    }
}
