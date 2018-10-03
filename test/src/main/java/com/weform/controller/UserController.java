package com.weform.controller;

import com.weform.common.response.RespCode;
import com.weform.common.response.ResponseEntity;
import com.weform.service.user.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: 简单DI年华
 * @Date: 18-9-27 00:31
 * @Description:
 */

@RestController
@RequestMapping("/user")
@Api(tags = "用户接口",value = "UserController")
public class UserController {

    @Autowired
    UserService userService;


    @ApiOperation(value = "用户登录",notes = "用户未登录过即登录，并返回userid，如果已经登陆过，返回userid")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="name",dataType="String",required=true,value="用户姓名",defaultValue="hcy"),
            @ApiImplicitParam(paramType="query",name="code",dataType="String",required=true,value="用户code",defaultValue="1312"),
            @ApiImplicitParam(paramType="query",name="gender",dataType="String",required=true,value="性别",defaultValue="男"),
            @ApiImplicitParam(paramType="query",name="avatar",dataType="String",required=true,value="头像",defaultValue="https://fanyi.baidu.com/"),
            @ApiImplicitParam(paramType="query",name="province",dataType="String",required=true,value="省份",defaultValue="anhui"),
            @ApiImplicitParam(paramType="query",name="city",dataType="String",required=true,value="城市",defaultValue="tongcheng"),
            @ApiImplicitParam(paramType="query",name="country",dataType="String",required=true,value="国家",defaultValue="china"),

    })
    @RequestMapping(value="/login",method= RequestMethod.GET)
    public ResponseEntity getUser(@RequestParam("name") String name,
                                  @RequestParam("code") String code,
                                  @RequestParam("gender") String gender,
                                  @RequestParam("avatar") String avatar,
                                  @RequestParam("province") String province,
                                  @RequestParam("city") String city,
                                  @RequestParam("country") String country) {

      Integer userid = userService.addUser(name, code, gender, avatar, province, city, country);
        return  new ResponseEntity(RespCode.SUCCESS,userid);
    }

}
