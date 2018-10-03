package com.weform.service.user;

import com.weform.WeformApplication;
import com.weform.mapper.UserMapper;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * @Auther: 简单DI年华
 * @Date: 18-9-27 19:41
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeformApplication.class)
public class UserServiceImplTest {


    @Autowired
    UserService userService;

    @Test
    public void getUseidByOpenid() {
        System.out.println(userService);
        System.out.println(userService.getUseidByOpenid("231"));
        System.out.println(userService.getUseidByOpenid("1234"));
    }

    @Test
    public void addUser() {
       int i =  userService.addUser("hcy","231","男","https://img-blog.csdn.net/20151116160828768",
        "安徽","芜湖","中国");
        System.out.println(i);
    }

    @Test
    public void getAllUsers() {
    }

    @Test
    public void getUserByUserid() {
    }
}