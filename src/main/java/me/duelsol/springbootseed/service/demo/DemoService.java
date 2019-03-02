package me.duelsol.springbootseed.service.demo;

import me.duelsol.springbootseed.entity.demo.Demo;

import java.util.List;

/**
 * @author 冯奕骅
 */
public interface DemoService {

    List<Demo> findAllDemos();

    Demo createDemo(int amount, String detail);

}
