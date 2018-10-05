package com.weform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.weform.common.response.RespCode;
import com.weform.common.response.ResponseEntity;
import com.weform.model.Article;
import com.weform.model.Tag;
import com.weform.service.article.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-5 11:22
 * @Description:
 */

@RestController
@RequestMapping("/article")
@Api(tags = "文章接口",value = "ArticleController")
public class ArticleController {

    @Autowired
    ArticleService articleService;




    @ApiOperation(value = "插入文章",notes = "返回标签集合")
    @ApiImplicitParams({
    })
    @RequestMapping(value="/add",method= RequestMethod.GET)
    public ResponseEntity insertArticle(@Param("title") String title,
                                        @Param("content") String content,
                                        @Param("userid") Integer userid,
                                        @Param("intro") String intro,
                                        @Param("tags") String tags,
                                        @Param("link") String link,
                                        @Param("iconurl") String iconurl) {


       Integer back =  articleService.addArticle(title,content, userid,intro,tags,link,iconurl);


        return  new ResponseEntity(RespCode.SUCCESS,back);
    }

    @ApiOperation(value = "查找文章",notes = "返回文章内容")
    @ApiImplicitParams({
    })
    @RequestMapping(value="/find",method= RequestMethod.GET)
    public ResponseEntity insertArticle(@Param("id") Integer id
                                       ) {

        Map articleById = articleService.getArticleById(id);

        return  new ResponseEntity(RespCode.SUCCESS,articleById);
    }
}
