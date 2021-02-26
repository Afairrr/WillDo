package com.afair.pojo.entity;

import lombok.Builder;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * @author WangBing
 * @date 2020/12/18 14:52
 */
@Data
@Builder
@Document(collection = "user")
public class User {
    @Id
    String id;
    private String name;
    @BsonProperty(value = "student_id")
    private String studentId;
    @BsonProperty(value = "class_id")
    private String classId;
    private int age;
    private int sex;
    private String location;
    @Version
    private Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return age == user.age &&
                sex == user.sex &&
                id.equals(user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(studentId, user.studentId) &&
                Objects.equals(classId, user.classId) &&
                Objects.equals(location, user.location) &&
                Objects.equals(version, user.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, studentId, classId, age, sex, location, version);
    }
}
