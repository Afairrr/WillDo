package com.afair.service.impl;

import com.afair.pojo.entity.User;
import com.afair.service.MongodbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * @author WangBing
 * @date 2020/12/18 14:49
 */
@Service
public class MongoTestImpl implements MongodbService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}
