package me.duelsol.springbootseed.service.demo;

import me.duelsol.springbootseed.dto.demo.DemoDto;

import java.util.List;

/**
 * @author 冯奕骅
 */
public interface IDemoService {

    List<DemoDto> findAllDemos();

    DemoDto createDemo(int amount, String detail);

}
