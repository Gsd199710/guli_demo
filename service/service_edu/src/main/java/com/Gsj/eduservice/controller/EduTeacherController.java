package com.Gsj.eduservice.controller;


import com.Gsj.eduservice.entity.EduTeacher;
import com.Gsj.eduservice.service.EduTeacherService;
import com.Gsj.eduservice.vo.TeacherQuery;
import com.Gsj.servicebase.exception.GuliException;
import com.Gsj.utils.Results;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author test.java
 * @since 2023-03-01
 */
@Api(tags = "讲师管理")
//@Api(tags = "讲师管理"),@ApiOperation(value = "所有讲师列表"),@ApiParam(name="id",value ="讲师id",required = true)
//设置swaggerApi属性的注解
@RestController
@RequestMapping("/eduservice/edu-teacher")
@CrossOrigin//解决跨域问题
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;
    //1.查询所有讲师数据
    @GetMapping("findAll")
    @ApiOperation(value = "所有讲师列表")
    public Results findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);

        return Results.success().data("items",list);
    }
    //2.逻辑删除讲师信息
    @DeleteMapping("{id}")
    @ApiOperation(value = "逻辑删除讲师")
    public Results deleteTeacher(@ApiParam(name="id",value ="讲师id",required = true) @PathVariable("id") String id){
        boolean b = eduTeacherService.removeById(id);
        if (b) {
            return Results.success();
        }
        return Results.error();
    }
    //3.分页查询讲师数据
    @GetMapping("pageTeacher/{current}/{limit}")
    @ApiOperation(value = "分页查询讲师数据")
    public Results pageListTeacher(@PathVariable("current")Long current,
                                   @PathVariable("limit")Long limit){
        //创建Page对象
        Page<EduTeacher> page = new Page<>(current,limit);
        eduTeacherService.page(page,null);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        return Results.success().data("total",total).data("rows",records);
    }
    //4.条件查询带分页功能
    @PostMapping("pageTeacher/{current}/{limit}")
    @ApiOperation(value = "条件查询带分页功能")
    public Results pageTeacherCondition(@PathVariable("current")Long current,
                                        @PathVariable("limit")Long limit,
                                        @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> page = new Page<>(current,limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件是否为空，非空则拼接条件
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified",end);
        }
        //添加按时间降序排序功能
        wrapper.orderByDesc("gmt_create");
        eduTeacherService.page(page,wrapper);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        return Results.success().data("total",total).data("rows",records);
    }
    //5.添加讲师
    @PostMapping("addTeacher")
    @ApiOperation(value = "添加讲师")
    public Results addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return Results.success();
        }
        return Results.error();
    }
    //6.根据讲师id查询
    @GetMapping("getTeacher/{id}")
    @ApiOperation(value = "根据讲师id查询")
    public Results getTeacher(@PathVariable("id") String id){
//        try {
//            int i = 10 / 0;//测试统一处理异常组件
//        }catch (Exception e){
//            throw new GuliException(1001,"执行了GuliException异常处理");
//        }
        EduTeacher teacher = eduTeacherService.getById(id);
        return Results.success().data("teacher",teacher);
    }
    //7.修改讲师信息
    @PostMapping("updateTeacher")
    @ApiOperation(value = "修改讲师信息")
    public Results updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean update = eduTeacherService.updateById(eduTeacher);
        if (update) {
            return Results.success();
        }
        return Results.error();
    }
}

