package me.duelsol.springbootseed.dto.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.duelsol.springbootseed.framework.support.ResponseData;

import java.util.List;

/**
 * @author 冯奕骅
 */
@Getter
@Setter
@AllArgsConstructor
public class DemoListDTO extends ResponseData {

    private List<DemoBean> data;

}
