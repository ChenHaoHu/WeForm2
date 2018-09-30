package com.weform.mapper;

import com.weform.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @Auther: 简单DI年华
 * @Date: 18-9-27 00:06
 * @Description: user表的DAO层
 */

@Mapper
@Component(value = "userMapper")
public interface UserMapper {

    //增加用户
    @Insert("INSERT INTO user(openid,name,avatar,gender,province,city,country,time)" +
            " VALUES(#{user.openid},#{user.name},#{user.avatar}," +
            "#{user.gender},#{user.province},#{user.city},#{user.country},#{user.time});")
    @Options(useGeneratedKeys = true, keyProperty = "user.userid")
    public int insertUser(@Param("user") User user);

    //通过openid寻找用户
    @Select("SELECT userid FROM user WHERE openid = #{openid}")
    public Integer[] findUseridByOpenid(@Param("openid")String openid);

}
