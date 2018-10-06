package com.weform.service.article;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.weform.mapper.ArticleMapper;
import com.weform.mapper.TagMapper;
import com.weform.mapper.UserMapper;
import com.weform.model.Article;
import com.weform.model.Tag;
import com.weform.model.User;
import com.weform.service.time.TimeUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-5 11:18
 * @Description:
 */

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    TimeUtil timeUtil;

    @Override
    public List<Article> getAllArticle() {
        return null;
    }

    @Override
    public Map getArticleById(Integer id) {
        Article articleById = articleMapper.getArticleById(id);
        User userByUserid = userMapper.findUserByUserid(articleById.getUserid() + "");
        Map map = new HashMap();
        map.put("article",articleById);
        map.put("user",userByUserid);

        return map;
    }

    @Override
    public Integer addArticle(String title,
                              String content,
                              Integer userid,
                              String intro,
                              String tags,
                              String link,
                              String iconurl
   ) {

        JSONArray jsonArray = JSON.parseArray(tags);

        //更新tag表
        for (int i = 0; i < jsonArray.size(); i++) {
            tagMapper.updateTagsNum(jsonArray.get(i).toString());
        }

        Article article = new Article(title, content, userid, timeUtil.getNowTime(), tags, intro,  link, iconurl);

        articleMapper.insertArticle(article);
        return article.getId();
    }

    @Override
    public List getArticleByTag(String tag) {
        String str = "select * from article WHERE JSON_CONTAINS(tags, '[\""+tag+"\"]')";
        List<Article> articleByTag = articleMapper.getArticleByTag(str);
        List<Map> back = new ArrayList<>();
        for (int i = 0; i < articleByTag.size(); i++) {
            Integer userid = articleByTag.get(i).getUserid();
            User userByUserid = userMapper.findUserByUserid(userid + "");
            Map map = new HashMap();
            map.put("username",userByUserid.getName());
            map.put("usericon",userByUserid.getAvatar());
            map.put("title",articleByTag.get(i).getTitle());
            map.put("intro",articleByTag.get(i).getIntro());
            map.put("icon",articleByTag.get(i).getIconurl());
            map.put("id",articleByTag.get(i).getId());
            map.put("time",articleByTag.get(i).getTime());
            map.put("type","分享");
            back.add(map);
        }
        return back;
    }

    @Override
    public Integer getNum() {
        Integer articleNum = articleMapper.getArticleNum();
        return articleNum;
    }

    @Override
    public List getExcellentArticle(Integer level) {
        List excellentArticleByLevel = articleMapper.getExcellentArticleByLevel(level);
        return excellentArticleByLevel;
    }


    @Override
    public List getArticleByUserid(Integer userid) {
        List articleByUserid = articleMapper.getArticleByUserid(userid);
        return articleByUserid;
    }

    @Override
    public boolean zanArticle(Integer id) {
        articleMapper.updateZanNum(id);
        return true;
    }
}
