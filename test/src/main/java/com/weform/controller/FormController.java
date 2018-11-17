package com.weform.controller;

import com.weform.common.request.RequestForm;
import com.weform.common.response.RespCode;
import com.weform.common.response.ResponseEntity;
import com.weform.model.Article;
import com.weform.model.Form;
import com.weform.service.form.FormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        password = password.toUpperCase();
        Form formByPassword = formService.getFormByPassword(password);

        return  new ResponseEntity(RespCode.SUCCESS,formByPassword);
    }

    @ApiOperation(value = "表单主题查询",notes = "根据表单id查找表单主题")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="formid",dataType="int",required=true,value="表单id",defaultValue="1018"),
    })
    @RequestMapping(value="/title",method= RequestMethod.GET)
    public ResponseEntity getTitleByFormid(Integer formid) {

        String formTitle = formService.getFormTitle(formid);

        return  new ResponseEntity(RespCode.SUCCESS,formTitle);
    }


    @ApiOperation(value = "验证表单是否准在",notes = "返回boolean")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="password",dataType="string",required=true,value="表单密匙",defaultValue="CHDF"),
    })
    @RequestMapping(value="/check",method= RequestMethod.GET)
    public ResponseEntity checkForm(String password) {

        password = password.toUpperCase();

        boolean b = formService.checkForm(password);

        return  new ResponseEntity(RespCode.SUCCESS,b);
    }

    @ApiOperation(value = "根据表单formid获取password",notes = "返回password")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="formid",dataType="int",required=true,value="表单id",defaultValue="test"),
    })
    @RequestMapping(value="/password",method= RequestMethod.GET)
    public ResponseEntity getPasswordByFormTitle(Integer formid) {

        String passwordByFormId = formService.getPasswordByFormId(formid);

        return  new ResponseEntity(RespCode.SUCCESS,passwordByFormId);
    }

    @ApiOperation(value = "根据userid获取自己报名的表单",notes = "返回list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="userid",dataType="int",required=true,value="用户id",defaultValue="1000"),
    })
    @RequestMapping(value="/user/build",method= RequestMethod.GET)
    public ResponseEntity getFormByUserid(Integer userid) {
        List formByUserid =  formService.getFormByUserid(userid);
        return  new ResponseEntity(RespCode.SUCCESS,formByUserid);
    }

    @ApiOperation(value = "分页查找表单数据",notes = "返回集合")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="pageSize",dataType="int",required=true,value="查询大小",defaultValue="1"),
            @ApiImplicitParam(paramType="query",name="pageNum",dataType="int",required=true,value="查询页码",defaultValue="2"),
    })
    @RequestMapping(value="/form/page",method= RequestMethod.GET)
    public ResponseEntity geFormByPages(Integer pageSize,Integer pageNum) {
        List<Form> formByPage = formService.getFormByPage( pageSize, pageNum);
        Map map = new HashMap();
        map.put("data",formByPage);
        map.put("pageNum",pageNum);
        map.put("pageSize",formByPage.size());
        return  new ResponseEntity(RespCode.SUCCESS,map);
    }

    @ApiOperation(value = "分页查找活动数据",notes = "返回集合")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="pageSize",dataType="int",required=true,value="查询大小",defaultValue="1"),
            @ApiImplicitParam(paramType="query",name="pageNum",dataType="int",required=true,value="查询页码",defaultValue="2"),
    })
    @RequestMapping(value="/activity/page",method= RequestMethod.GET)
    public ResponseEntity getActivitryByPages(Integer pageSize,Integer pageNum) {
        List<Form> activityByPage = formService.getActivityByPage( pageSize, pageNum);
        Map map = new HashMap();
        map.put("data",activityByPage);
        map.put("pageNum",pageNum);
        map.put("pageSize",activityByPage.size());
        return  new ResponseEntity(RespCode.SUCCESS,map);
    }

}
