package me.duelsol.springbootseed.service.demo;

import me.duelsol.springbootseed.dto.demo.DemoDTO;
import me.duelsol.springbootseed.dto.demo.DemoListDTO;

/**
 * @author 冯奕骅
 */
public interface DemoService {

    DemoListDTO findAllDemos();

    DemoDTO createDemo(int amount, String detail);

}
