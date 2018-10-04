package com.weform.service.join;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weform.mapper.FormMapper;
import com.weform.mapper.JoinMapper;
import com.weform.mapper.UserMapper;
import com.weform.model.Join;
import com.weform.model.User;
import com.weform.service.poi.ExcelUtil;
import com.weform.service.time.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-3 23:12
 * @Description: 报名操作
 *
 */
@Service
public class JoinServiceImpl implements JoinService {

    @Autowired
    JoinMapper joinMapper;

    @Autowired
    FormMapper formMapper;

    @Autowired
    TimeUtil timeUtil;

    @Autowired
    ExcelUtil excelUtil;

    @Autowired
    UserMapper userMapper;


    @Override
    public Integer joinForm(String formid, String userid, String content) {
        JSONObject jsonObject = JSON.parseObject(content);
        Join join = new Join(formid,userid,jsonObject.toJSONString(),timeUtil.getNowTime(),false);
        joinMapper.addJoinData(join);
        return join.getId();
    }

    @Override
    public List<Join> getJoinData(String formid, String userid) {
        return null;
    }

    @Override
    public String deleteJoinData(String id) {

        Integer formid = joinMapper.getFormidById(id);
        String back = formMapper.getPasswordByFormid(formid);
        joinMapper.deleteDataById(id);

        return back;
    }

    @Override
    public String getJoinPassword(String id) {

        Integer formid = joinMapper.getFormidById(id);
        String back = formMapper.getPasswordByFormid(formid);
        return back;
    }

    @Override
    public String exportJoinData(Integer formid) {

        List<Join> joins = joinMapper.getJoinDataByFormid(formid);
       String back =  excelUtil.export(joins);

        return back;
    }

    @Override
    public List getUserDataByFormid(Integer formid) {

        List<Map> back = new ArrayList();

        List<String> userlist = joinMapper.getUseridsById(formid);

        System.out.println(userlist.size());
        for (int i = 0; i < userlist.size(); i++) {
            User user = userMapper.findUserByUserid(userlist.get(i));
            System.out.println(user);
            Map map = new HashMap();
            map.put("name",user.getName());
            map.put("iconurl",user.getAvatar());
            back.add(map);
        }

        return back;
    }
}
