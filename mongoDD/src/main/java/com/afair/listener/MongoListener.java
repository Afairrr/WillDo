package com.afair.listener;

import com.afair.pojo.entity.User;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;

/**
 * @author WangBing
 * @date 2020/12/18 16:06
 */
public class MongoListener extends AbstractMongoEventListener<User> {
    /**
     * 主要可以进行审核操作 设置时间戳等等
     * Called in MongoTemplate insert, insertList, and save
     * operations before the object is converted to a Document by a MongoConverter.
     *
     * @param event
     */
    @Override
    public void onBeforeConvert(BeforeConvertEvent<User> event) {
        super.onBeforeConvert(event);
    }

    /**
     * 改变值 将其删除等等
     *
     * @param event
     */
    @Override
    public void onBeforeSave(BeforeSaveEvent<User> event) {
        super.onBeforeSave(event);
    }
}
