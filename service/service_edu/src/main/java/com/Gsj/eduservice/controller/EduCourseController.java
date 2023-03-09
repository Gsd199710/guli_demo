package com.Gsj.eduservice.controller;


import com.Gsj.eduservice.service.EduCourseService;
import com.Gsj.eduservice.vo.CourseInfoVo;
import com.Gsj.utils.Results;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author test.java
 * @since 2023-03-09
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Resource
    private EduCourseService courseService;

    @PostMapping("addCourseInfo")
    public Results addCourse(@RequestBody CourseInfoVo courseInfoVo){
        //扩展添加课程小结功能需要返回课程id
        String id = courseService.saveCourse(courseInfoVo);
        return Results.success().data("courseId",id);
    }
}

