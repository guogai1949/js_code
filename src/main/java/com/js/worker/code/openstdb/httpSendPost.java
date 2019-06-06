package com.js.worker.code.openstdb;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
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
 
public class httpSendPost {
 
    public static String sendPost(String url,JSONArray list) throws IOException {
        String result = null;
        HttpPost httpPost = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        httpPost = new HttpPost(url);
        String string = JSON.toJSONString(list);
 
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
    
    public static String sendPost(String url) throws IOException {
        String result = null;
        HttpGet httpPost = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        httpPost = new HttpGet(url);
 
        HttpResponse resp = httpClient.execute(httpPost);
        System.out.println(resp);
 
            HttpEntity he  =resp.getEntity();
            result = EntityUtils.toString(he,"UTF-8");
 
 
        return result;
    }
    
    public static List<Object> setList(){
        Map<String,Object> map =new HashMap<String,Object>();
        Map<String,Object> map1 = new HashMap<>();
        map.put("metric","self.test");
        map.put("timestamp",System.currentTimeMillis());
        map.put("value",4);
        map1.put("LineName","S16");
//        map1.put("BoardName","G-97RG3_15K-00007-AM2(1.8)_HDC000449-TOP");
        map1.put("BarCode","081820403239");
        map1.put("Division","4");
        map1.put("Tower","4");
        map1.put("Level","4");
        map1.put("ComponentName","012-10121-24");
        map.put("tags",map1);
 
 
        Map<String,Object> map_1 =new HashMap<String,Object>();
        Map<String,Object> map1_1 = new HashMap<>();
        map_1.put("metric","self.test");
        map_1.put("timestamp",System.currentTimeMillis());
        map_1.put("value",5);
        map1_1.put("LineName","S16");
//        map1_1.put("BoardName","G-97RG3_15K-00007-AM2(1.8)_HDC000449-TOP");
        map1_1.put("BarCode","081820403239");
        map1_1.put("Division","5");
        map1_1.put("Tower","5");
        map1_1.put("Level","5");
        map1_1.put("ComponentName","012-10121-24");
        map_1.put("tags",map1_1);
 
 
        Map<String,Object> map_2 =new HashMap<String,Object>();
        Map<String,Object> map1_2 = new HashMap<>();
        map_2.put("metric","self.test");
        map_2.put("timestamp",System.currentTimeMillis());
        map_2.put("value",6);
        map1_2.put("LineName","S16");
//        map1_2.put("BoardName","G-97RG3_15K-00007-AM2(1.8)_HDC000449-TOP");
        map1_2.put("BarCode","081820403239");
        map1_2.put("Division","6");
        map1_2.put("Tower","6");
        map1_2.put("Level","6");
        map1_2.put("ComponentName","012-10121-24");
        map_2.put("tags",map1_2);
 
 
        List<Object> list = new ArrayList<>();
        list.add(map);
        list.add(map_1);
        list.add(map_2);
        return list;
 
 
 
    }
 
 
    public static void main(String[] args) {
        List<Object> list = setList();
//        Map param = new HashMap<>();
//        param.put("start", "2019/05/27-10:20:00");
//        param.put("m", "sum:self.test");
        try {
            String str = sendPost("http://192.168.171.54:4242/api/put?details",JSONArray.parseArray(JSON.toJSONString(list)));
            System.out.println(str);
//            String str1 = sendPost("http://192.168.171.54:4242/api/query?start=2019/05/27-10:20:00&m=sum:self.test");
//            System.out.println(str1);
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
}