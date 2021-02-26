package com.afair.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.geometry.Point;
import org.elasticsearch.index.VersionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

/**
 * @author WangBing
 * @date 2020/12/25 15:58
 */
@Document(indexName = "area", shards = 5)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TypeAlias("area")
public class Area {
    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String zipCode;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String code;
    @GeoPointField
    private Point location;
}
