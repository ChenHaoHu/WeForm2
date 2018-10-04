package com.weform.service.poi;

import com.weform.WeformApplication;
import com.weform.mapper.JoinMapper;
import com.weform.model.Join;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.Action;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-4 11:12
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeformApplication.class)
public class ExcelUtilImplTest {

    @Autowired
    ExcelUtil excelUtil;
    @Autowired
    JoinMapper joinMapper;

    @Test
    public void init() {
    }

    @Test
    public void export() {
//        List<Join> joinDataByFormid = joinMapper.getJoinDataByFormid("1018");
//        excelUtil.export(joinDataByFormid);
    }
}