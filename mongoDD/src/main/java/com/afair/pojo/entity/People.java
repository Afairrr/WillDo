package com.afair.pojo.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author WangBing
 * @date 2020/12/26 12:49
 */
@Data
@Builder
public class People {
    private int age;
    private String name;
    private String sex;
}
