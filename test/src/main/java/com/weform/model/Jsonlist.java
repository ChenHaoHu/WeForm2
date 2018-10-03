package com.weform.model;

/**
 * @Auther: 简单DI年华
 * @Date: 18-9-27 00:03
 * @Description: 用户填写加入的数据表
 * */
public class Jsonlist {

    /**
     *   `id` int(11) NOT NULL,
     *   `formid` int(11) NULL DEFAULT NULL,
     *   `userid` int(11) NULL DEFAULT NULL,
     *   `content` json NULL,
     *   `time` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
     */
    //编号
    private Integer id;
    //表单的id
    private Integer formid;
    //用户id
    private Integer userid;
    //内容 json
    private String content;
    //加入时间
    private String time;

    public Jsonlist(Integer id, Integer formid, Integer userid, String content, String time) {
        this.id = id;
        this.formid = formid;
        this.userid = userid;
        this.content = content;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
