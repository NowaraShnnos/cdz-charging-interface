package com.pengniao.cdzcharginginterface.util;

import com.pengniao.cdzcharginginterface.entity.StartChargeResult;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: cdz-charging-interface
 * @description: 外部系统接口调用
 * @author: lj
 * @create: 2020-11-20 09:40
 **/
@Component
public class InterfaceCall {

    public static String sendPost(String url, final Map<String,String> head, final StartChargeResult parameter) throws Exception {
        boolean isSuccess = false;
        String str = "";
        HttpPost post = null;
        HttpClient httpClient = new DefaultHttpClient();
        // 设置超时时间
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 6000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 6000);

        post = new HttpPost(url);
        // 构造消息头

        post.setHeader("Content-type", "application/json; charset=utf-8");
        for (Map.Entry<String, String> entry : head.entrySet()) {
            post.setHeader(entry.getKey(), entry.getValue());
        }
        // 构建消息实体
        // 发送Json格式的数据请求
        System.out.println(System.currentTimeMillis());
        HttpResponse response = httpClient.execute(post);
        System.out.println(System.currentTimeMillis());
        // 检验返回码
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            isSuccess = false;
        } else {
            int retCode = 0;
            /**读取服务器返回过来的json字符串数据**/
            str = EntityUtils.toString(response.getEntity());
        }
        if (post != null) {
            try {
                post.releaseConnection();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return str;
    }
}
