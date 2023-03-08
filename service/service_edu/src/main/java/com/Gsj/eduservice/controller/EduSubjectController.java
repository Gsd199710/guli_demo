package com.Gsj.eduservice.controller;


import com.Gsj.eduservice.entity.subject.OneSubject;
import com.Gsj.eduservice.service.EduSubjectService;
import com.Gsj.utils.Results;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author test.java
 * @since 2023-03-07
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Resource
    private EduSubjectService subjectService;

    //添加课程分类
    @PostMapping("addSubject")
    public Results addSubject(MultipartFile file){
        //上传过来的文件
        subjectService.saveSubject(file,subjectService);
        return Results.success();
    }
    //树形课程分类列表
    @GetMapping("getAllSubject")
    public Results getAllSubject(){
        List<OneSubject> list = subjectService.getAllSubjects();
        return Results.success().data("list",list);
    }
}

