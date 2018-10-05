package com.weform.service.article;

import com.weform.model.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-5 11:14
 * @Description:
 */

@Service
public interface ArticleService {

    List<Article> getAllArticle();

    Map getArticleById(Integer id);

    Integer addArticle(String title, String content,Integer userid,String intro,String tags,
                       String link,String iconurl);

    List getArticleByTag(String tag);

}
