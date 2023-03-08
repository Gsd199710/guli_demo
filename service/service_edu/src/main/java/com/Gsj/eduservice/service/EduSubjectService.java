package com.Gsj.eduservice.service;

import com.Gsj.eduservice.entity.EduSubject;
import com.Gsj.eduservice.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author test.java
 * @since 2023-03-07
 */
public interface EduSubjectService extends IService<EduSubject> {
    //添加课程方法
    void saveSubject(MultipartFile file,EduSubjectService subjectService);
    //获取课程列表方法
    List<OneSubject> getAllSubjects();
}
