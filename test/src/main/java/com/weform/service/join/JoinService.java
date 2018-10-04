package com.weform.service.join;

import com.weform.model.Join;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-3 23:08
 * @Description: 报名操作接口
 */

@Service
public interface JoinService {

    Integer joinForm(String formid,String userid,String content);

    List<Join> getJoinData(String formid, String userid);

    String deleteJoinData(String id);

    String exportJoinData(Integer formid);

    List getUserDataByFormid(Integer formid);

    String getJoinPassword(String id);

}
