package com.weform.service.form;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weform.common.request.RequestForm;
import com.weform.mapper.FormMapper;
import com.weform.mapper.UserMapper;
import com.weform.model.Article;
import com.weform.model.Form;
import com.weform.model.User;
import com.weform.service.math.MathUtil;
import com.weform.service.time.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-1 12:05
 * @Description: 表单操作实体类
 */

@Service
public class FormServiceImpl implements FormService {


    //引入表单操作DAO
    @Autowired
    FormMapper formMapper;
    //注入时间操作
    @Autowired
    TimeUtil timeUtil;
    //注入数学运算
    @Autowired
    MathUtil mathUtil;
    //注入人员信息
    @Autowired
    UserMapper userMapper;


    @Override
    public String addForm(RequestForm data) {

        Form form = new Form(data.getUserid(),data.getTitle(),data.getType(),
               data.getMode(),data.getStart(),data.getEnd(),timeUtil.getNowTime(),
                data.getMaxnum(),data.getIntro(),data.getIconurl(), data.getPosterurl(),
                data.getIspublic(),data.getUsername(),data.getTags());

        formMapper.insertform(form);
       String password =  mathUtil.getFormPassWord(form.getFormid());

       formMapper.addpassword(form.getFormid(),password);

        JSONObject jsonObject  = new JSONObject();
        jsonObject.put("formid",form.getFormid());
        jsonObject.put("password",password);

        return jsonObject.toJSONString();
    }

    /**
     * 根据密码获取信息
     * @param password
     * @return
     */
    @Override
    public Form getFormByPassword(String password) {
           Form form  = formMapper.getFromByPassword(password);
        return form;
    }

    @Override
    public String getFormTitle(Integer formid) {

        String titleByFormid = formMapper.getTitleByFormid(formid + "");
        return titleByFormid;
    }

    @Override
    public List getFormByTag(String tag) {

        List<Form> articleByTag = formMapper.getFormByTag(tag);
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
            map.put("id",articleByTag.get(i).getFormid());
            map.put("type","form");
            back.add(map);
        }

        return back;
    }

    @Override
    public String deleForm() {
        return null;
    }

    @Override
    public String editForm() {
        return null;
    }
}
