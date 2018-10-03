package com.weform.service.user;

import com.weform.mapper.UserMapper;
import com.weform.model.User;
import com.weform.service.wxapi.WxApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息操作类
 * @Auther: 简单DI年华
 * @Date: 18-9-27 19:23
 * @Description:  用户信息操作类
 */

@Service
public class UserServiceImpl implements UserService {

    //注入DAO
    @Autowired
    UserMapper userMapper;
    //注入WxApi
    @Autowired
    WxApi wxApi;

    /**
     * 获取用户的userid，也是验证用户是否已经存在
     * @param code
     * @return 返回 -1 表示未找到此人
     *         返回 非-1 表示对应openid的userid
     */
    @Override
    public Integer getUseidByOpenid(String code) {
        String openid = wxApi.getOpenid(code);
        Integer[] userid = userMapper.findUseridByOpenid(openid);
        if(userid.length!=1){
            return -1;
        }else {
            return userid[0];
        }
    }

    /**
     * 添加用户信息
     * @param name
     * @param code
     * @param gender
     * @param avatar
     * @param province
     * @param city
     * @param country
     * @return
     */
    @Override
    public Integer addUser(String name,String code,String gender,String avatar,
                           String province,String city,String country) {

        String openid = wxApi.getOpenid(code);
        User user =  new User(openid,name,avatar,gender,province,city,country);
        userMapper.insertUser(user);
        return user.getuserid();
    }

    /**
     * 查询所有用户信息
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUserByUserid() {
        return null;
    }
}
