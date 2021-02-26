package com.afair.pojo.entity;

import lombok.Data;

/**
 * 抽象函数
 *
 * @author WangBing
 * @date 2021/1/7 16:09
 */
@Data
public class Abstraction {
    /**
     * id
     */
    private long id;
    /**
     * 名字
     */
    private String name;
    /**
     * 聚合条件
     */
    private Integer aggregateType;
    /**
     * 搜索字段
     */
    private String searchField;
    /**
     * 搜索字段的类型
     */
    private Integer searchIntervalType;
    /**
     * 搜索字段的值
     */
    private Integer searchIntervalValue;
    /**
     * 时间的类型
     */
    private Integer timeIntervalType;
    /**
     * 时间的具体值
     */
    private Integer timeIntervalValue;
}
