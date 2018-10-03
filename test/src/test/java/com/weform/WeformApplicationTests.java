package com.weform;

import com.weform.service.user.UserService;
import com.weform.service.user.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeformApplicationTests {

   @Autowired
    UserService userService;

    @Test
    public void getUseidByOpenid() {
        System.out.println(userService);
        System.out.println(userService.getUseidByOpenid("da"));
    }
}
