package com.weform.model;

/**
 * @Auther: 简单DI年华
 * @Date: 18-9-26 20:26
 * @Description: 表单的实体类
 *                  记录表单的一些详细信息
 */
public class Form {
    //表单id
    private Integer formid;
    //创建者id
    private Integer userid;
    //表单标题
    private String title;
    //表单类型 调查问卷 活动报名 文章分享
    private String type;
    //表单内容
    private String mode;
    //表单开始报名时间
    private String start;
    //表单结束报名时间
    private String end;
    //表单创建时间
    private String create;
    //表单最大报名人数
    private Integer maxnum;
    //表单的简介
    private String intro;
    //表单的头像  限制一张 大小限制 300*300的正方形
    private String iconurl;
    //表单的附加文件 可以有数个 ['/doc/1000(id)/test.doc','/doc/1000(id)/test.doc']
    private String docurl;
    //表单进去的密匙
    private String password;
    //点赞用户id列表  ['1000','1001','1003']
    private String starlist;
    //表单的海报轮播 可以有数个 ['/porter/1000(id)/test.doc','/doc/1000(id)/test.doc']
    private String posterurl;
    //签到表的id 如果没有就为 -1
    private Integer signid;

    public Form(Integer formid, Integer userid, String title, String type, String mode, String start, String end, String create, Integer maxnum, String intro, String iconurl, String docurl, String password, String starlist, String posterurl, Integer signid) {
        this.formid = formid;
        this.userid = userid;
        this.title = title;
        this.type = type;
        this.mode = mode;
        this.start = start;
        this.end = end;
        this.create = create;
        this.maxnum = maxnum;
        this.intro = intro;
        this.iconurl = iconurl;
        this.docurl = docurl;
        this.password = password;
        this.starlist = starlist;
        this.posterurl = posterurl;
        this.signid = signid;
    }

    public Integer getFormid() {
        return formid;
    }

    public void setFormid(Integer formid) {
        this.formid = formid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public Integer getMaxnum() {
        return maxnum;
    }

    public void setMaxnum(Integer maxnum) {
        this.maxnum = maxnum;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public String getDocurl() {
        return docurl;
    }

    public void setDocurl(String docurl) {
        this.docurl = docurl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStarlist() {
        return starlist;
    }

    public void setStarlist(String starlist) {
        this.starlist = starlist;
    }

    public String getPosterurl() {
        return posterurl;
    }

    public void setPosterurl(String posterurl) {
        this.posterurl = posterurl;
    }

    public Integer getSignid() {
        return signid;
    }

    public void setSignid(Integer signid) {
        this.signid = signid;
    }
}
