package com.weform.service.form;

import com.weform.common.request.RequestForm;
import com.weform.model.Form;
import org.springframework.stereotype.Service;

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

    String deleForm();

    String editForm();
}
