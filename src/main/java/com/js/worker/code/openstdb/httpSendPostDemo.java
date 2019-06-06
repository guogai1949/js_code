package com.js.worker.code.openstdb;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class httpSendPostDemo {
 
    public static String sendPost(String url,String string) throws IOException {
        String result = null;
        HttpPost httpPost = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        httpPost = new HttpPost(url);
        
        StringEntity entity = new StringEntity(string,"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
 
        HttpResponse resp = httpClient.execute(httpPost);
        System.out.println(resp);
 
            HttpEntity he  =resp.getEntity();
            result = EntityUtils.toString(he,"UTF-8");
 
 
        return result;
    }
 
 
 
    public static void main(String[] args) {
		String string = "{\"metric\":\"test\",\"value\":\"10.0\",\"timestamp\":1523415635034,\"tags\":{\"fid\":\"1\",\"td\":\"test1\"}}";
        try {
            String str = sendPost("http://192.168.171.54:4242/api/put?details",string);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
}