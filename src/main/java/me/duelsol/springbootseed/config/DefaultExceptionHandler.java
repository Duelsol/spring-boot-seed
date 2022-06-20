package me.duelsol.springbootseed.config;

import lombok.extern.slf4j.Slf4j;
import me.duelsol.springbootseed.framework.support.ResponseCode;
import me.duelsol.springbootseed.framework.support.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 冯奕骅
 */
@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseData defaultExceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseData.of(ResponseCode.ERROR);
    }

}
