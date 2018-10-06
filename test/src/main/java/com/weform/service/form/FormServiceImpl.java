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

        List<Form> formByTag = formMapper.getFormByTag(tag);
        List<Map> back = new ArrayList<>();
        for (int i = 0; i < formByTag.size(); i++) {
            Integer userid = formByTag.get(i).getUserid();
            User userByUserid = userMapper.findUserByUserid(userid + "");
            Map map = new HashMap();
            map.put("username",userByUserid.getName());
            map.put("usericon",userByUserid.getAvatar());
            map.put("title",formByTag.get(i).getTitle());
            map.put("intro",formByTag.get(i).getIntro());
            map.put("icon",formByTag.get(i).getIconurl());
            //这里把表单的密匙当作id传，便于前端处理
            map.put("id",formByTag.get(i).getPassword());
            map.put("time",formByTag.get(i).getCreatetime());
            map.put("type","表单");
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

    @Override
    public boolean checkForm(String password) {
        Form fromByPassword = formMapper.getFromByPassword(password);

        if(fromByPassword == null){
            return false;
        }

        return true;
    }

    @Override
    public Map getNum() {
        Integer form = formMapper.getFormNum();
        Integer activity = formMapper.getAvtivityNum();
        Map map = new HashMap();
        map.put("form",form);
        map.put("activity",activity);

        return map;
    }

    @Override
    public String getPasswordByFormTitle(String title) {
        String passwordByFormTitle = formMapper.getPasswordByFormTitle(title);

        return passwordByFormTitle;
    }

    @Override
    public List getFormByUserid(Integer useid) {
        List<Form> formByUserid = formMapper.getFormByUserid(useid);
        return formByUserid;
    }
}
