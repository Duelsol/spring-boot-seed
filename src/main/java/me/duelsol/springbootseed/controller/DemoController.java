package me.duelsol.springbootseed.controller;

import lombok.extern.slf4j.Slf4j;
import me.duelsol.springbootseed.entity.Demo;
import me.duelsol.springbootseed.framework.support.ResponseData;
import me.duelsol.springbootseed.service.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 冯奕骅
 */
@RestController
@Slf4j
public class DemoController {

    @Autowired
    private DemoRepository demoRepository;

    @GetMapping(value = "list")
    public Object list() {
        return new ResponseData(demoRepository.findAll());
    }

    @GetMapping(value = "save")
    public Object save() {
        Demo demo = new Demo();
        demo.setAmount(100);
        demo.setDetail("Detail");
        return new ResponseData(demoRepository.saveAndFlush(demo));
    }

}
