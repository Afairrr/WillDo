package com.afair;

import com.afair.pojo.entity.Area;
import com.afair.pojo.entity.People;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.geometry.Point;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author WangBing
 * @date 2020/12/26 10:56
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {
    @Autowired
    @Qualifier("elasticsearchClient")
    private RestHighLevelClient client;

    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @Autowired
    private ActionListener listener;

    @Test
    public void indexTest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (int i = 0; i < 10; i++) {
            IndexRequest request = new IndexRequest("people");
            People people = People.builder().age(23 + i).name("mexx" + i).sex("女").build();
            request.source(JSON.toJSONString(people), XContentType.JSON).timeout(TimeValue.timeValueSeconds(3));
            bulkRequest.add(request);
        }
        bulkRequest.timeout(TimeValue.timeValueMinutes(2));
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.status());
    }

    @Test
    public void asyncIndexTest() {
        IndexRequest request = new IndexRequest("people");
        People people = People.builder().age(42).name("youyuyu").sex("女").build();
        request.source(JSON.toJSONString(people), XContentType.JSON).timeout(TimeValue.timeValueSeconds(3));
        client.indexAsync(request, RequestOptions.DEFAULT, listener);
    }

    @Test
    public void getIndexTest() throws IOException {
        GetRequest request = new GetRequest("people", "1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSource());
    }

    @Test
    public void searchTest() throws IOException {
        SearchRequest searchRequest = new SearchRequest("people");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(5);
        searchSourceBuilder.timeout(new TimeValue(3, TimeUnit.SECONDS));
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }

    @Test
    public void searchTest2() throws IOException {
        SearchRequest searchRequest = new SearchRequest("people");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "me").fuzziness(Fuzziness.AUTO);
        searchSourceBuilder.query(matchQueryBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(5);
        searchSourceBuilder.timeout(new TimeValue(3, TimeUnit.SECONDS));
//        searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }

    @Test
    public void templateTest() {
        restTemplate.indexOps(Area.class);
        Area area = new Area();
        area.setId(2L);
        area.setLocation(new Point(31.91833, 94.05316));
        area.setZipCode("331004");
        area.setCode("我是蒙多");
        restTemplate.save(area);
    }

    @Test
    public void templateSearchTest() {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("code", "我"));
        NativeSearchQuery query = nativeSearchQueryBuilder.build();
        org.springframework.data.elasticsearch.core.SearchHits<Area> search = restTemplate.search(query, Area.class);
        for (org.springframework.data.elasticsearch.core.SearchHit<Area> searchHit : search.getSearchHits()) {
            System.out.println(searchHit.getContent());
        }
    }

    @Test
    public void templateSearchAggTest() {
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("zipCodes").field("zipCode");
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().addAggregation(aggregationBuilder);
        NativeSearchQuery query = searchQueryBuilder.build();
        org.springframework.data.elasticsearch.core.SearchHits<Area> search = restTemplate.search(query, Area.class);
        for (org.springframework.data.elasticsearch.core.SearchHit<Area> hit : search) {
            System.out.println(hit.getContent());
        }

    }
}
