package com.Gsj.eduservice.service;

import com.Gsj.eduservice.entity.EduCourse;
import com.Gsj.eduservice.vo.CourseInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author test.java
 * @since 2023-03-09
 */
public interface EduCourseService extends IService<EduCourse> {
    //添加课程基本信息的方法
    String saveCourse(CourseInfoVo courseInfoVo);
}
