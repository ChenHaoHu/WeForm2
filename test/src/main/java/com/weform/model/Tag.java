package com.weform.model;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-5 09:06
 * @Description: 标签的实体类
 */
public class Tag {

    private Integer id;
    private String name;
    private String  time;
    //已有项目
    private Integer num;

    public Tag() {
    }

    public Tag(String name, String time, Integer num) {
        this.name = name;
        this.time = time;
        this.num = num;
    }

    public Tag(Integer id, String name, String time, Integer num) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.num = num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
