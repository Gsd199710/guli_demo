package com.Gsj.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OneSubject {
    private String id;
    private String title;
    //一级列表可以对应多个二级列表
    private List<TwoSubject> children = new ArrayList<>();
}
