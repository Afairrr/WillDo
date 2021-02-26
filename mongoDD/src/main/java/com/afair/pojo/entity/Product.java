package com.afair.pojo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author WangBing
 * @date 2020/12/22 10:47
 */
@Data
public class Product {
    @Id
    String id;
    int age;
}
