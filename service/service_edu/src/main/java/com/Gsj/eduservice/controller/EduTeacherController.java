package com.Gsj.eduservice.controller;


import com.Gsj.eduservice.entity.EduTeacher;
import com.Gsj.eduservice.service.EduTeacherService;
import com.Gsj.utils.Results;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

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
    //分页查询讲师数据
    @GetMapping("pageTeacher/{current}/{limit}")
    public Results pageListTeacher(@PathVariable("current")Long current,
                                   @PathVariable("limit")Long limit){
        //创建Page对象
        Page<EduTeacher> page = new Page<>(current,limit);
        eduTeacherService.page(page,null);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();

        return Results.success().data("total",total).data("rows",records);
    }
}

