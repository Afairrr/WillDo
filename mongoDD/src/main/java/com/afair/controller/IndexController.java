package com.afair.controller;

import com.afair.common.utils.HtmlParseUtils;
import com.afair.pojo.entity.JdGoods;
import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author WangBing
 * @date 2020/12/28 14:25
 */
@Controller
public class IndexController {
    @Autowired
    @Qualifier("elasticsearchClient")
    private RestHighLevelClient client;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/index")
    public String index() {
        kafkaTemplate.send("foo", "wo ai laohu");
        return "good/index";
    }

    @GetMapping("/es/insert/{keyword}/{page}")
    @ResponseBody
    public void esInsertBatch(@PathVariable("keyword") String keyword, @PathVariable("page") Integer page) throws Exception {
        List<JdGoods> jdGoods = HtmlParseUtils.JdParse(keyword, page);
        BulkRequest bulkRequest = new BulkRequest();
        for (JdGoods jdGood : jdGoods) {
            IndexRequest request = new IndexRequest("jd_goods");
            request.source(JSON.toJSONString(jdGood), XContentType.JSON).timeout(TimeValue.timeValueSeconds(3));
            bulkRequest.add(request);
        }
        bulkRequest.timeout(TimeValue.timeValueMinutes(2));
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.status());
    }

    @GetMapping("/search/{keyword}/{pageNum}/{pageSize}")
    @ResponseBody
    public List<JdGoods> searchKey(@PathVariable("keyword") String keyword, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) throws Exception {
        if (pageNum == null) {
            pageNum = 1;
            pageSize = 10;
        }
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("name", keyword));
        searchSourceBuilder.from(pageNum);
        searchSourceBuilder.size(pageSize);
        searchSourceBuilder.timeout(new TimeValue(3, TimeUnit.SECONDS));
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        SearchHit[] hits1 = hits.getHits();
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<JdGoods> jdGoods = new ArrayList<>();
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            JdGoods jdGood = new JdGoods();
            BeanUtils.populate(jdGood, sourceAsMap);
            jdGoods.add(jdGood);
        }
        return jdGoods;
    }
}
