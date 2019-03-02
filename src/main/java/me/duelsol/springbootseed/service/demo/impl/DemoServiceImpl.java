package me.duelsol.springbootseed.service.demo.impl;

import me.duelsol.springbootseed.dao.demo.DemoRepository;
import me.duelsol.springbootseed.entity.demo.Demo;
import me.duelsol.springbootseed.service.demo.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 冯奕骅
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoRepository demoRepository;

    @Override
    public List<Demo> findAllDemos() {
        return demoRepository.findAll();
    }

    @Override
    public Demo createDemo(int amount, String detail) {
        Demo demo = new Demo();
        demo.setAmount(amount);
        demo.setDetail(detail);
        return demoRepository.saveAndFlush(demo);
    }

}
