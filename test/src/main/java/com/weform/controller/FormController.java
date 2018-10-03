package com.weform.controller;

import com.weform.common.request.RequestForm;
import com.weform.common.response.RespCode;
import com.weform.common.response.ResponseEntity;
import com.weform.model.Form;
import com.weform.service.form.FormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-1 13:14
 * @Description: 表单操作接口
 */

@RestController
@RequestMapping("/form")
@Api(tags = "表单接口",value = "FormController")
public class FormController {
    @Autowired
    FormService formService;

    @ApiOperation(value = "表单提交",notes = "提交表单 返回id和password")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="userid",dataType="String",required=true,value="用户id",defaultValue="123"),
            @ApiImplicitParam(paramType="query",name="title",dataType="String",required=true,value="表单名称",defaultValue="表单名称"),
            @ApiImplicitParam(paramType="query",name="intro",dataType="String",required=true,value="介绍",defaultValue="介绍"),
            @ApiImplicitParam(paramType="query",name="type",dataType="String",required=true,value="类型",defaultValue="form"),
            @ApiImplicitParam(paramType="query",name="mode",dataType="String",required=true,value="表单模板",defaultValue="['123','21']"),
            @ApiImplicitParam(paramType="query",name="start",dataType="String",required=true,value="开始时间",defaultValue="2019-10-3"),
            @ApiImplicitParam(paramType="query",name="end",dataType="String",required=true,value="结束时间",defaultValue="2023-10-3"),
            @ApiImplicitParam(paramType="query",name="maxnum",dataType="String",required=true,value="最多人数",defaultValue="12"),
            @ApiImplicitParam(paramType="query",name="iconurl",dataType="String",required=true,value="头像",defaultValue="iconurl"),
            @ApiImplicitParam(paramType="query",name="posterurl",dataType="String",required=true,value="海报(可重复)",defaultValue="['123','21']"),
            @ApiImplicitParam(paramType="query",name="ispublic",dataType="String",required=true,value="是否公开",defaultValue="false"),
            @ApiImplicitParam(paramType="query",name="tags",dataType="String",required=true,value="标签",defaultValue="测试"),
            @ApiImplicitParam(paramType="query",name="username",dataType="String",required=true,value="用户名",defaultValue="hcy")
    })
    @RequestMapping(value="/add",method= RequestMethod.GET)
    public ResponseEntity getUser(RequestForm data) {

       String str =  formService.addForm(data);

        return  new ResponseEntity(RespCode.SUCCESS,str);
    }

    @ApiOperation(value = "表单查找",notes = "根据表单密匙查找表单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="password",dataType="String",required=true,value="表单密匙",defaultValue="BIHJ"),
    })
    @RequestMapping(value="/find/password",method= RequestMethod.GET)
    public ResponseEntity getFormByPassword(String password) {

        Form formByPassword = formService.getFormByPassword(password);

        return  new ResponseEntity(RespCode.SUCCESS,formByPassword);
    }
}
