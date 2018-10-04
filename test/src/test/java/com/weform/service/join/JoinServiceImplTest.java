package com.weform.service.join;

import com.weform.WeformApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-4 17:35
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeformApplication.class)
public class JoinServiceImplTest {

    @Autowired
    JoinService joinService;

    @Test
    public void getUserDataByFormid() {

        System.out.println(joinService);
        List list = joinService.getUserDataByFormid(1018);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }

    }
}