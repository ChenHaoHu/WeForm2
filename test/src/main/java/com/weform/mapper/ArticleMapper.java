package com.weform.mapper;

import com.weform.model.Article;
import com.weform.model.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-5 11:18
 * @Description: 文章DAO
 */

@Mapper
@Component(value = "articleMapper")
public interface ArticleMapper {

    @Insert("INSERT INTO article(title,content,userid,time,tags,intro,link,iconurl) VALUES(" +
            "#{article.title},#{article.content},#{article.userid},#{article.time}" +
            ",#{article.tags},#{article.intro},#{article.link},#{article.iconurl})")
    @Options(useGeneratedKeys = true, keyProperty = "article.id")
    Integer insertArticle(@Param("article") Article article);


    //根据id获取文章
    @Select("SELECT * FROM article WHERE id = #{id} LIMIT 1;")
    Article getArticleById(@Param("id")Integer id);

    //根据tag查找文章
    @Select("#{tag}")
    List<Article> getArticleByTag(@Param("tag")String tag);
}
