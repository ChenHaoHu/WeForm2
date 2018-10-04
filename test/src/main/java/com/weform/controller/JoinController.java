package com.weform.controller;

import com.weform.common.response.RespCode;
import com.weform.common.response.ResponseEntity;
import com.weform.service.join.JoinService;
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

import java.util.List;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-3 23:22
 * @Description: 报名操作接口
 */

@RestController
@RequestMapping("/join")
@Api(tags = "报名操作接口",value = "JoinController")
public class JoinController {

    @Autowired
    JoinService joinService;

    @ApiOperation(value = "报名",notes = "报名，返回id")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="formid",dataType="String",required=true,value="表单id",defaultValue="3123"),
            @ApiImplicitParam(paramType="query",name="userid",dataType="String",required=true,value="用户id",defaultValue="3132"),
            @ApiImplicitParam(paramType="query",name="content",dataType="String",required=true,value="表单内容",defaultValue="[123,134]"),
    })
    @RequestMapping(value="/form",method= RequestMethod.GET)
    public ResponseEntity joinForm(@RequestParam("formid")String formid,
                                  @RequestParam("userid")String userid,
                                  @RequestParam("content")String content
                                 ) {
        Integer back =  joinService.joinForm(formid,userid,content);
        return  new ResponseEntity(RespCode.SUCCESS,back);
    }


    @ApiOperation(value = "删除报名",notes = "返回password")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="id",dataType="String",required=true,value="报名id",defaultValue="1010"),
    })
    @RequestMapping(value="/delete",method= RequestMethod.GET)
    public ResponseEntity deletejoinForm(@RequestParam("id")String id) {
        String back =  joinService.deleteJoinData(id);
        return  new ResponseEntity(RespCode.SUCCESS,back);
    }

    @ApiOperation(value = "报名密匙",notes = "返回password")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="id",dataType="String",required=true,value="报名id",defaultValue="1010"),
    })
    @RequestMapping(value="/password",method= RequestMethod.GET)
    public ResponseEntity getjoinpassword(@RequestParam("id")String id) {
        String back =  joinService.getJoinPassword(id);
        return  new ResponseEntity(RespCode.SUCCESS,back);
    }


    @ApiOperation(value = "导出EXCEL表格",notes = "返回表格路径")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="formid",dataType="Integer",required=true,value="表单id",defaultValue="1010"),
    })
    @RequestMapping(value="/export",method= RequestMethod.GET)
    public ResponseEntity exportExcel(@RequestParam("formid") Integer formid) {
        String back =  joinService.exportJoinData(formid);
        return  new ResponseEntity(RespCode.SUCCESS,back);
    }


    @ApiOperation(value = "获取所有报名信息",notes = "返回报名信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="formid",dataType="Integer",required=true,value="表单id",defaultValue="1010"),
    })
    @RequestMapping(value="/user",method= RequestMethod.GET)
    public ResponseEntity getUserDataByFormid(@RequestParam("formid") Integer formid) {
        List back =  joinService.getUserDataByFormid(formid);
        return  new ResponseEntity(RespCode.SUCCESS,back);
    }

}
