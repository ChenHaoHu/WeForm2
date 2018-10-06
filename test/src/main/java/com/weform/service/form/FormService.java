package com.weform.service.form;

import com.weform.common.request.RequestForm;
import com.weform.model.Form;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-1 12:03
 * @Description: 操作表单的接口
 */

@Service
public interface FormService {

    String addForm(RequestForm requestForm);

    Form getFormByPassword(String password);

    String getFormTitle(Integer formid);

    List getFormByTag(String tag);

    String deleForm();

    String editForm();

    boolean checkForm(String password);

    Map getNum();

    String getPasswordByFormTitle(String title);

    List getFormByUserid(Integer useid);
}
