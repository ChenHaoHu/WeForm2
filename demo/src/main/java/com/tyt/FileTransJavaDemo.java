package com.tyt;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
public class FileTransJavaDemo {
    /**
     * 地域ID
     * 常量内容，请勿改变
     */
    public static final String REGIONID = "cn-shanghai";
    public static final String ENDPOINTNAME = "cn-shanghai";
    public static final String PRODUCT = "nls-filetrans";
    public static final String DOMAIN = "filetrans.cn-shanghai.aliyuncs.com";
    public static final String API_VERSION = "2018-08-17";
    public static final String POST_REQUEST_ACTION = "SubmitTask";
    public static final String GET_REQUEST_ACTION = "GetTaskResult";
    /**
     * 参数设置Key
     * 常量内容，请勿改变
     */
    public static final String KEY_APP_KEY = "app_key";
    public static final String KEY_FILE_LINK = "file_link";
    public static final String KEY_TASK = "Task";
    public static final String KEY_TASK_ID = "TaskId";
    public static final String KEY_STATUS_TEXT = "StatusText";
    public static void main(String args[]) throws Exception {
        if (args.length < 3) {
            System.err.println("FileTransJavaDemo need params: <AccessKey Id> <AccessKey Secret> <app-key>");
        }
        final String accessKeyId = "LTAI5y6NsFd6eFCI";
        final String accessKeySecret = "PCB1hr1gstL1HGo8r3kzq99qOwk7fc";
        final String appKey = "WR4xyZhc4HGAlTRG";
        /**
         * 阿里云鉴权client
         */
        IAcsClient client;
        // 设置endpoint
        DefaultProfile.addEndpoint(ENDPOINTNAME, REGIONID, PRODUCT, DOMAIN);
        // 创建DefaultAcsClient实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile(REGIONID, accessKeyId, accessKeySecret);
        client = new DefaultAcsClient(profile);
        /**
         * 创建CommonRequest 设置请求参数
         */
        CommonRequest postRequest = new CommonRequest();
        // 设置域名
        postRequest.setDomain(DOMAIN);
        // 设置API的版本号，格式为YYYY-MM-DD
        postRequest.setVersion(API_VERSION);
        // 设置action
        postRequest.setAction(POST_REQUEST_ACTION);
        // 设置产品名称
        postRequest.setProduct(PRODUCT);
        /**
         * 设置录音文件识别请求参数，以JSON字符串的格式设置到请求的Body中
         */
        JSONObject taskObject = new JSONObject();
        // 设置app_key
        taskObject.put(KEY_APP_KEY, appKey);
        // 设置音频文件访问链接
        taskObject.put(KEY_FILE_LINK, "https://www.hcyang.top/file/images/1540017261200.mp3");
        String task = taskObject.toJSONString();
        // 设置以上JSON字符串为Body参数
        postRequest.putBodyParameter(KEY_TASK, task);
        // 设置为POST方式的请求
        postRequest.setMethod(MethodType.POST);
        /**
         * 提交录音文件识别请求
         */
        // 获取录音文件识别请求任务的ID，以供识别结果查询使用
        String taskId = "";
        CommonResponse postResponse = client.getCommonResponse(postRequest);
        if (postResponse.getHttpStatus() == 200) {
            JSONObject result = JSONObject.parseObject(postResponse.getData());
            String statusText = result.getString(KEY_STATUS_TEXT);
            if (statusText.equals("SUCCESS")) {
                System.out.println("录音文件识别请求成功响应： " + result.toJSONString());
                taskId = result.getString(KEY_TASK_ID);
            }
            else {
                System.err.println("录音文件识别请求失败： " + postResponse.getData());
                return;
            }
        }
        else {
            System.err.println("录音文件识别请求失败，Http错误码：" + postResponse.getHttpStatus());
            System.err.println("录音文件识别请求失败响应：" + postResponse.getData());
            return;
        }
        /**
         * 创建CommonRequest 设置任务ID
         */
        CommonRequest getRequest = new CommonRequest();
        // 设置域名
        getRequest.setDomain(DOMAIN);
        // 设置API版本
        getRequest.setVersion(API_VERSION);
        // 设置action
        getRequest.setAction(GET_REQUEST_ACTION);
        // 设置产品名称
        getRequest.setProduct(PRODUCT);
        // 设置任务ID为查询参数
        getRequest.putQueryParameter(KEY_TASK_ID, taskId);
        // 设置为GET方式的请求
        getRequest.setMethod(MethodType.GET);
        /**
         * 提交录音文件识别结果查询请求
         * 以轮询的方式进行识别结果的查询，直到服务端返回的状态描述为“SUCCESS”、“SUCCESS_WITH_NO_VALID_FRAGMENT”，或者为错误描述，则结束轮询。
         */
        String statusText = "";
        while (true) {
            CommonResponse getResponse = client.getCommonResponse(getRequest);
            if (getResponse.getHttpStatus() != 200) {
                System.err.println("识别结果查询请求失败，Http错误码：" + getResponse.getHttpStatus());
                System.err.println("识别结果查询请求失败：" + getResponse.getData());
                break;
            }
            JSONObject result = JSONObject.parseObject(getResponse.getData());
            System.out.println("识别查询结果：" + result.toJSONString());
            statusText = result.getString(KEY_STATUS_TEXT);
            if (statusText.equals("RUNNING") || statusText.equals("QUEUEING")) {
                // 继续轮询
                Thread.sleep(3000);
            }
            else {
                break;
            }
        }
        if (statusText.equals("SUCCESS") || statusText.equals("SUCCESS_WITH_NO_VALID_FRAGMENT")) {
            System.out.println("录音文件识别成功！");
        }
        else {
            System.err.println("录音文件识别失败！");
        }
    }
}