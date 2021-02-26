package com.afair.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @author WangBing
 * @date 2020/12/25 11:13
 */
@Configuration
@Slf4j
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    @Value("${spring.data.elasticsearch.client.reactive.username}")
    private String username;
    @Value("${spring.data.elasticsearch.client.reactive.password}")
    private String password;
    @Value("${elasticsearch.url}")
    private String url;


    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        HttpHost.create(this.url))
        );
        return client;
    }
}
