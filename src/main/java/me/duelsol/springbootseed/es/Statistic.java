package me.duelsol.springbootseed.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

/**
 * @author 冯奕骅
 */
@Data
@Document(indexName = "statistic-#{esIndexSuffix.getDate()}")
public class Statistic {

    @Id
    private String id;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'+08:00'")
    private LocalDateTime time;

    private String name;

    private int score;

}
