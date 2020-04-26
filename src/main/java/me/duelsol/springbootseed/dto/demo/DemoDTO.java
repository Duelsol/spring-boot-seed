package me.duelsol.springbootseed.dto.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.duelsol.springbootseed.framework.support.ResponseData;

/**
 * @author 冯奕骅
 */
@Getter
@Setter
@AllArgsConstructor
public class DemoDTO extends ResponseData {

    private DemoBean data;

}
