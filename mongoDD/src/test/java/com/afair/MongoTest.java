package com.afair;

import com.afair.pojo.entity.Product;
import com.afair.pojo.entity.User;
import com.afair.repository.UserRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;
import com.mongodb.client.model.Aggregates.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.eq;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;


/**
 * @author WangBing
 * @date 2020/12/18 14:54
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTest {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    UserRepository userRepository;

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            int age = (int) (Math.random() * 50);
            String name = "robotTwo" + age;
            User wangxx = User.builder().age(age).name(name).sex(1).studentId(new Random(100).toString()).build();
            mongoTemplate.insert(wangxx);
        }
////        mongoTemplate.query(User.class).matching(query(where("age").lt(12))).all();
//        mongoTemplate.findOne(query(where("id").is(wangxx.getId())),User.class);
//        List<User> all = mongoTemplate.findAll(User.class);
    }

    @Test
    public void pageSort() {
        Page<User> allBySex = userRepository.findAllBySex(1, PageRequest.of(0, 5));
        allBySex.getContent().forEach(info -> System.out.println(info));
    }

    @Test
    public void update() {
//        User wangxx = User.builder().age(12).name("wangxx").sex(1).build();
        Query age = new Query(where("age").is(10));
//
//        Update update = new Update();
//        update.set("age",33);
//        update.set("name","daxigua");
//        mongoTemplate.updateFirst(age,update,User.class);
        mongoTemplate.remove(age, User.class);
    }

    @Test
    public void update2() {
        MongoCollection<Document> user = mongoTemplate.getCollection("user");
        FindIterable<Document> documents = user.find();
        documents.forEach(info -> System.out.println(info));
//        user.distinct()
    }

    @Test
    public void regex() {
        //java层面处理模糊逻辑
        Document document = new Document();
        Pattern pattern = Pattern.compile("xx", Pattern.CASE_INSENSITIVE);
        document.append("name", pattern);
        MongoCollection<Document> user = mongoTemplate.getCollection("user");
        FindIterable<Document> documents = user.find(document);
        documents.forEach(info -> System.out.println(info));
    }

    @Test
    public void regex2() {
        //mongo层面处理模糊逻辑
        Document document = new Document();
        document.append("name", new Document("$regex", "xx"));
        MongoCollection<Document> user = mongoTemplate.getCollection("user");
        FindIterable<Document> documents = user.find(document);
        documents.forEach(info -> System.out.println(info));
    }

    @Test
    public void regex3() {
        String[] categorys = new String[]{"wa", "xg"};
        List<Pattern> list = new ArrayList<>();
        for (String index : categorys) {
            Pattern pattern = Pattern.compile(index, Pattern.CASE_INSENSITIVE);
            list.add(pattern);
        }
        Document document = new Document();
        document.append("name", new BasicDBObject("$in", list));
        MongoCollection<Document> user = mongoTemplate.getCollection("user");
        FindIterable<Document> documents = user.find(document);
        documents.forEach(info -> System.out.println(info));
    }

    @Test
    public void aggregate() {
        MongoCollection<Document> user = mongoTemplate.getCollection("user");
        TypedAggregation<User> name = Aggregation.newAggregation(User.class, Aggregation.match(where("name").regex("ro")),
                Aggregation.group("name").sum("age").as("age"));
        AggregationResults<Product> user1 = mongoTemplate.aggregate(name, Product.class);
        List<Product> mappedResults = user1.getMappedResults();
        mappedResults.forEach(System.out::println);
    }

    @Test
    public void aggregate2() {
        //返回新的数据
//        User newValue = mongoTemplate.update(User.class)
//                .matching(Query.query(where("name").is("robotTwo9")))
//                .apply(Update.update("age", 12))
//                .withOptions(FindAndModifyOptions.options().upsert(true).returnNew(true))
//                .findAndModifyValue();
//        System.out.println(newValue);

        //返回老的数据
//        User oldValue = mongoTemplate.findAndModify(Query.query(where("name").is("robotTwo9")), Update.update("age", 22), User.class);
//        System.out.println(oldValue);
        Bson bson = Aggregates.match(eq("name", "robot"));
    }

}
