package com.weform.service.article;

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
 * @Date: 18-10-5 21:02
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeformApplication.class)
public class ArticleServiceImplTest {

    @Autowired
    ArticleService articleService;

    @Test
    public void getArticleByTag() {

//
//        List data = articleService.getArticleByTag(str);
//        System.out.println(data.toString());


    }
}