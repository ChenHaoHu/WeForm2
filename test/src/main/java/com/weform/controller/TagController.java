package com.weform.controller;

import com.weform.common.response.RespCode;
import com.weform.common.response.ResponseEntity;
import com.weform.model.Tag;
import com.weform.service.article.ArticleService;
import com.weform.service.form.FormService;
import com.weform.service.tag.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-5 09:11
 * @Description: 标签操作接口
 */

@RestController
@RequestMapping("/tag")
@Api(tags = "标签接口",value = "TagController")
public class TagController {



    @Autowired
    TagService tagService;
    @Autowired
    ArticleService articleService;
    @Autowired
    FormService formService;


    @ApiOperation(value = "查找标签",notes = "返回标签集合")
    @ApiImplicitParams({
    })
    @RequestMapping(value="/all",method= RequestMethod.GET)
    public ResponseEntity findAllTags() {

        List<Tag> tags = tagService.finAllTags();

        return  new ResponseEntity(RespCode.SUCCESS,tags);
    }

    @ApiOperation(value = "根据标签查找表单/文章",notes = "返回集合")
    @ApiImplicitParams({
    })
    @RequestMapping(value="/value",method= RequestMethod.GET)
    public ResponseEntity findAllByTags(String tag) {

        List articleByTag = articleService.getArticleByTag(tag);
        List formByTag = formService.getFormByTag(tag);

        articleByTag.addAll(formByTag);

        return  new ResponseEntity(RespCode.SUCCESS,articleByTag);
    }

}
