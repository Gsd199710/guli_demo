package com.Gsj.eduservice.service.impl;

import com.Gsj.eduservice.entity.EduCourse;
import com.Gsj.eduservice.entity.EduCourseDescription;
import com.Gsj.eduservice.mapper.EduCourseMapper;
import com.Gsj.eduservice.service.EduCourseDescriptionService;
import com.Gsj.eduservice.service.EduCourseService;
import com.Gsj.eduservice.vo.CourseInfoVo;
import com.Gsj.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author test.java
 * @since 2023-03-09
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Resource
    private EduCourseDescriptionService courseDescriptionService;

    @Override
    public String saveCourse(CourseInfoVo courseInfoVo) {
        //添加课程表的信息
        EduCourse eduCourse = new EduCourse();
        //将courseInfoVo转换成功insert()所需的EduCourse对象
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);//执行添加方法
        if (insert == 0) {
            throw new GuliException(1001,"添加课程出现未知异常");
        }
        //获取添加课程后的id
        String cid = eduCourse.getId();

        //添加课程简介
        //添加课程简介依赖于EduCourseDescriptionService的业务接口
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //手动设置简介id为课程id
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);

        return cid;
    }
}
