package me.duelsol.springbootseed.es;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 冯奕骅
 */
@Component
public class EsIndexSuffix {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public String getDate() {
        return FORMATTER.format(LocalDateTime.now());
    }

}
