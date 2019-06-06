package com.js.worker.code.es;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * 
 * @Description: 获取Java High Level REST Client客户端
 * @author lgs
 * @date 2018年6月23日
 *
 */
public class InitDemo {

    public static RestHighLevelClient getClient() {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.171.54", 9200, "http")));

        return client;
    }
    
    public static void main(String[] args) {
        try (RestHighLevelClient client = InitDemo.getClient();) {

            // 1、创建 创建索引request 参数：索引名mess
            CreateIndexRequest request = new CreateIndexRequest("test");

            // 2、设置索引的settings
            request.settings(Settings.builder().put("index.number_of_shards", 3) // 分片数
                    .put("index.number_of_replicas", 3) // 副本数
//                    .put("analysis.analyzer.default.tokenizer", "ik_smart") // 默认分词器
            );

            // 3、设置索引的mappings
            request.mapping("_doc",
                    "  {\n" +
                    "    \"_doc\": {\n" +
                    "      \"properties\": {\n" +
                    "        \"message\": {\n" +
                    "          \"type\": \"text\"\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }",
                    XContentType.JSON);

            // 4、 设置索引的别名
            request.alias(new Alias("demo"));

            // 5、 发送请求
            // 5.1 同步方式发送请求
            CreateIndexResponse createIndexResponse = client.indices()
                    .create(request);

            // 6、处理响应
            boolean acknowledged = createIndexResponse.isAcknowledged();
            boolean shardsAcknowledged = createIndexResponse
                    .isShardsAcknowledged();
            System.out.println("acknowledged = " + acknowledged);
            System.out.println("shardsAcknowledged = " + shardsAcknowledged);

            // 5.1 异步方式发送请求
            /*ActionListener<CreateIndexResponse> listener = new ActionListener<CreateIndexResponse>() {
                @Override
                public void onResponse(
                        CreateIndexResponse createIndexResponse) {
                    // 6、处理响应
                    boolean acknowledged = createIndexResponse.isAcknowledged();
                    boolean shardsAcknowledged = createIndexResponse
                            .isShardsAcknowledged();
                    System.out.println("acknowledged = " + acknowledged);
                    System.out.println(
                            "shardsAcknowledged = " + shardsAcknowledged);
                }

                @Override
                public void onFailure(Exception e) {
                    System.out.println("创建索引异常：" + e.getMessage());
                }
            };

            client.indices().createAsync(request, listener);
            */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}