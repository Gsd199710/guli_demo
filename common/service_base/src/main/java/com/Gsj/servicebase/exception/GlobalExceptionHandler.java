package com.Gsj.servicebase.exception;


import com.Gsj.utils.Results;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody//返回异常数据
    public Results error(Exception e){
        e.printStackTrace();
        return Results.error().message("执行统一处理异常！");
    }
    //自定义异常处理方法
    @ExceptionHandler(GuliException.class)
    @ResponseBody//返回异常数据
    public Results error(GuliException e){
        e.printStackTrace();
        return Results.error().code(e.getCode()).message(e.getMsg());
    }
}
