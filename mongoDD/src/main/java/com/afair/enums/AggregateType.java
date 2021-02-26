package com.afair.enums;

/**
 * 聚合条件
 *
 * @author WangBing
 * @date 2021/1/7 16:05
 */
public class AggregateType {
    /**
     * 求总数
     */
    public static final int COUNT = 1;
    /**
     * 求去重的总数
     */
    public static final int DISTINCT_COUNT = 2;
    /**
     * 求和
     */
    public static final int SUM = 3;
    /**
     * 求平均
     */
    public static final int AVERAGE = 4;
    /**
     * 求最大值
     */
    public static final int MAX = 5;
    /**
     * 求最小值
     */
    public static final int MIN = 6;
}
