package com.weform.service.wxapi;

import org.springframework.stereotype.Service;

/**
 * @Auther: 简单DI年华
 * @Date: 18-8-23 16:00
 * @Description:
 */
@Service
public interface WxApi {
    String getOpenid(String code);
}
