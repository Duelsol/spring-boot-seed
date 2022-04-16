package me.duelsol.springbootseed.service.demo;

import me.duelsol.springbootseed.dto.demo.DemoBean;

import java.util.List;

/**
 * @author 冯奕骅
 */
public interface DemoService {

    List<DemoBean> findAllDemos();

    DemoBean createDemo(int amount, String detail);

}
