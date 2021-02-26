package com.afair.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author WangBing
 * @date 2020/12/25 15:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "jd_goods", useServerConfiguration = true)
public class JdGoods {
    String img;
    String name;
    String price;
}
