package com.afair.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * @author WangBing
 * @date 2021/1/12 14:38
 */
public class WordCount {
    public static void main(String[] args) {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        String filePath = "src\\main\\resources\\hello.txt";
        DataSource<String> data = env.readTextFile(filePath);
        AggregateOperator<Tuple2<String, Integer>> sum = data.flatMap(new MyFlatMapper())
                .groupBy(0)
                .sum(1);
        try {
            sum.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class MyFlatMapper implements FlatMapFunction<String, Tuple2<String, Integer>> {
        @Override
        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
            String[] words = s.split(" ");
            for (String word : words) {
                collector.collect(new Tuple2<>(word, 1));
            }
        }
    }
}
