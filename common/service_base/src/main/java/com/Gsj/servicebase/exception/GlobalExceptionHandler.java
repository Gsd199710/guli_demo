package com.Gsj.servicebase.exception;


import com.Gsj.utils.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j//开启logback日志监控
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
        log.error(e.getMessage());//logback自带的输出信息到日志文件中
        e.printStackTrace();
        return Results.error().code(e.getCode()).message(e.getMsg());
    }
}
