package com.weform.service.wxapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Auther: 简单DI年华
 * @Date: 18-8-23 16:02
 * @Description: 微信官方api处理
 */

@Service

public class WxApiImpl implements WxApi {
    @Value("${wxapp.appid}")
    String appid;//填写appid
    @Value("${wxapp.appsecret}")
    String appsecret;//填写对应appsecret

    @Override
    public String getOpenid(String code) {

        String reslut = "none";
//        DefaultHttpClient httpClient = new DefaultHttpClient();
//        HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/sns/jscode2session?appid="+ appid +
//                "&secret="+appsecret+"&js_code="+code+"&grant_type=authorization_code");
//        try{
//            HttpResponse httpResponse =  httpClient.execute(httpGet);
//            HttpEntity httpEntity = httpResponse.getEntity();
//            reslut = EntityUtils.toString(httpEntity);
//        }catch (Exception e){
//            System.out.println(e.getStackTrace());
//        }
        //使用okhttp
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.weixin.qq.com/sns/jscode2session?appid="+ appid +"&secret="+appsecret+"&js_code="+code+"&grant_type=authorization_code")
                    .build();
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            System.out.println(str);
            JSONObject json= JSON.parseObject(str);
            System.out.println(json);
            reslut = json.getString("openid");
            System.out.println(reslut);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return reslut;
    }
}
