package com.Gsj.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//返回统一结果类
@Data
public class Results {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回信息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> data = new HashMap<String,Object>();
    //私有构造方法
    private Results(){

    }
    //成功静态方法
    public static Results success(){
        Results results = new Results();
        results.setSuccess(true);
        results.setCode(ResultCode.SUCCESS);
        results.setMessage("成功");
        return results;
    }
    //失败静态方法
    public static Results error(){
        Results results = new Results();
        results.setSuccess(false);
        results.setCode(ResultCode.ERROR);
        results.setMessage("失败");
        return results;
    }
    //链式编程
    public Results success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Results message(String message){
        this.setMessage(message);
        return this;
    }

    public Results code(Integer code){
        this.setCode(code);
        return this;
    }

    public Results data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Results data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
