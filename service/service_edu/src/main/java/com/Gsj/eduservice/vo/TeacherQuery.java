package com.Gsj.eduservice.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TeacherQuery implements Serializable {
    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;
    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间",example = "2022-01-01 00:00")
    private String begin;
    @ApiModelProperty(value = "查询结束事件",example = "2023-01-01 00:00")
    private String end;
}
