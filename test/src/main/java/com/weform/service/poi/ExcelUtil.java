package com.weform.service.poi;

import com.weform.model.Join;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-4 10:52
 * @Description: Excel操作工具
 */

@Service
public interface ExcelUtil {
     void init();
     String export(List<Join> data);
}
