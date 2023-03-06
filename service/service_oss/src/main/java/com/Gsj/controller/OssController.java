package com.Gsj.controller;

import com.Gsj.service.OssService;
import com.Gsj.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;
    //上传头像方法
    @PostMapping
    public Results uploadOssFile(MultipartFile file){
        //获取上传文件
        String url = ossService.uploadFileAvatar(file);
        return Results.success().data("url",url);
    }
}
