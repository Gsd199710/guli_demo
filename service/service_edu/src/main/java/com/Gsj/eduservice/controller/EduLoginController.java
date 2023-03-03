package com.Gsj.eduservice.controller;

import com.Gsj.utils.Results;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin//解决跨域问题
public class EduLoginController {

    //login()
    @PostMapping("login")
    public Results login(){

        return Results.success().data("token","admin");
    }
    //info()
    @GetMapping("info")
    public Results info(){

        return Results.success()
                .data("roles","[admin]")
                .data("name","admin")
                .data("avatar","https://images.pexels.com/photos/11407391/pexels-photo-11407391.jpeg");
    }
}
