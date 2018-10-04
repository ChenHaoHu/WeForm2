package com.weform.mapper;

import com.weform.model.Form;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-1 12:16
 * @Description: 表单DAO层
 */

@Mapper
@Component(value = "formMapper")
public interface FormMapper {


    //增加表单
    @Insert("INSERT INTO form(userid,title,type,mode,start,end,maxnum,createtime,intro" +
            ",iconurl,posterurl,tags,ispublic,username)" +
            " VALUES(#{form.userid},#{form.title},#{form.type}," +
            "#{form.mode},#{form.start},#{form.end},#{form.maxnum},#{form.create}" +
            ",#{form.intro},#{form.iconurl},#{form.posterurl}" +
            ",#{form.tags},#{form.ispublic},#{form.username});")
    @Options(useGeneratedKeys = true, keyProperty = "form.formid")
     int insertform(@Param("form") Form form);

    //增加密匙
    @Update("UPDATE form SET password = #{password} WHERE formid = #{formid}")
     int addpassword(@Param("formid") Integer formid,
                           @Param("password") String password);

    //根据密匙获取信息
    @Select("SELECT * FROM form WHERE password = #{password} LIMIT 1;")
     Form getFromByPassword(@Param("password")String password);

    //根据formid获取title
    @Select("SELECT title FROM form WHERE formid = #{formid} LIMIT 1;")
     String getTitleByFormid(@Param("formid")String formid);

    //根据formid获取password
    @Select("SELECT password FROM form WHERE formid = #{formid} ;")
    String getPasswordByFormid(@Param("formid")Integer formid);
}
