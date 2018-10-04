package com.weform.service.form;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weform.common.request.RequestForm;
import com.weform.mapper.FormMapper;
import com.weform.model.Form;
import com.weform.service.math.MathUtil;
import com.weform.service.time.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public String addForm(RequestForm data) {

        Form form = new Form(data.getUserid(),data.getTitle(),data.getType(),
                JSON.toJSONString(data.getMode()),data.getStart(),data.getEnd(),timeUtil.getNowTime(),
                data.getMaxnum(),data.getIntro(),data.getIconurl(), JSON.toJSONString(data.getPosterurl()),
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
    public String deleForm() {
        return null;
    }

    @Override
    public String editForm() {
        return null;
    }
}
