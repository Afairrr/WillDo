package com.afair.service;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author WangBing
 * @date 2021/1/12 9:19
 */
public interface MongodbService {
    /**
     * mongodbTemplate获取
     *
     * @return
     */
    MongoTemplate getMongoTemplate();

    /**
     * 集合获取
     *
     * @param collectionName
     * @return
     */
    default MongoCollection<Document> getCollection(String collectionName) {
        return getMongoTemplate().getCollection(collectionName);
    }

    /**
     * 新增
     *
     * @param collectionName
     * @param doc
     */
    default void insert(String collectionName, Document doc) {
        MongoCollection<Document> collection = getCollection(collectionName);
        collection.insertOne(doc);
    }
}
