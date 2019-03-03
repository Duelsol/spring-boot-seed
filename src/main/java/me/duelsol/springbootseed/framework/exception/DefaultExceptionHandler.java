package me.duelsol.springbootseed.framework.exception;

import me.duelsol.springbootseed.framework.support.ResponseData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 冯奕骅
 */
@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseData defaultExceptionHandler(Exception ex) {
        return new ResponseData("100000", ex.getMessage());
    }

}
