package com.weform.service.user;

import com.weform.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 简单DI年华
 * @Date: 18-9-27 19:02
 * @Description: 用户处理接口
 */

@Service
public interface UserService {


    Integer getUseidByOpenid(String code);

    Integer addUser(String name,String code,String gender,String avatar,
                    String province,String city,String country);

    List<User> getAllUsers();

    User getUserByUserid();



}
