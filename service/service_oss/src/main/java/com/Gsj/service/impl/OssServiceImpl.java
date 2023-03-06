package com.Gsj.service.impl;

import com.Gsj.service.OssService;
import com.Gsj.utils.ConstantUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        //获取定义的工具类中的OSS属性值
        String endpoint = ConstantUtils.END_POINT;
        //云账号AccessKey所有API访问权限
        String keyid = ConstantUtils.ACCESS_KEY_ID;
        String keysecret = ConstantUtils.ACCESS_KEY_SECRET;
        String bucketname = ConstantUtils.BUCKET_NAME;

        try {
            //创建OSSclient实例
            OSS ossclient = new OSSClientBuilder().build(endpoint,keyid,keysecret);
            //上传文件流
            InputStream inputStream = file.getInputStream();
            //获取上传文件的名称
            String filename = file.getOriginalFilename();
            //为防止文件名出现重复再加上随机值
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            filename = uuid + filename;
            //按时间将文件进行分类处理
            String s = new DateTime().toString("yyyy/MM/dd");
            //进行拼接:20**/01/01/*******.jpg
            filename = s+"/"+filename;
            //调用oss上传方法
            ossclient.putObject(bucketname,filename,inputStream);
            //关闭ossclient
            ossclient.shutdown();

            //将上传oss的路径返回，需要手动拼接
            String url ="https://"+bucketname+"."+endpoint+"/"+filename;

            return url;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
