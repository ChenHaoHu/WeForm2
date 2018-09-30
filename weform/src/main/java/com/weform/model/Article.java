package com.weform.model;

/**
 * @Auther: 简单DI年华
 * @Date: 18-9-26 20:18
 * @Description: 用户分享文章的实体类
 */
public class Article {

    //文章id
    private Integer id;
    //文章标题
    private String title;
    //文章内容
    private String content;
    //作者id
    private Integer userid;
    //创建时间
    private String time;
    //点赞列表
    private String starlist;

    public Article(Integer id, String title, String content, Integer userid, String time, String starlist) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userid = userid;
        this.time = time;
        this.starlist = starlist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStarlist() {
        return starlist;
    }

    public void setStarlist(String starlist) {
        this.starlist = starlist;
    }
}
