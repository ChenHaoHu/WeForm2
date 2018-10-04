package com.weform.controller;

import com.weform.common.response.RespCode;
import com.weform.common.response.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @Auther: 简单DI年华
 * @Date: 18-9-12 19:17
 * @Description:
 */
@RestController
@RequestMapping("/common")
@Api(tags = "一般接口",value = "CommonControler")
public class CommonControler {

    @Value("${ip.home}")
    String home;

    @Value("${path.images}")
    String path;

    /**
     * 获取图片信息
     * 获取图片信息的同时是保存图片在服务器 并以身份证号命名
     * @param file
     * @return
     */

    @ApiOperation(value = "上传图片",notes = "上传图片 返回图片地址")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="file",dataType="string",required=true,value="file",defaultValue="123")
    })
    @PostMapping(value = "/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file){



        System.out.println(file.getOriginalFilename());
        String[] names = file.getOriginalFilename().split("\\.");
        String name = System.currentTimeMillis()+"."+names[names.length-1];

        try {
            InputStream inputStream = file.getInputStream();
            File out = new File(path+name);
            FileOutputStream outputStream = new FileOutputStream(out);
            int temp;
            while ((temp = inputStream.read())!= -1){
                outputStream.write(temp);
                outputStream.flush();
            }
            inputStream.close();
            outputStream.close();
            file.getInputStream().close();
        }catch (Exception e){
            System.out.println("出现错误");
            System.out.println(e.fillInStackTrace());
        }
        return new ResponseEntity(RespCode.SUCCESS,home+"images/"+name);
    }
}
