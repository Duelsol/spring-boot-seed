package me.duelsol.springbootseed.controller.demo;

import lombok.extern.slf4j.Slf4j;
import me.duelsol.springbootseed.framework.support.ResponseData;
import me.duelsol.springbootseed.service.demo.DemoService;
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
    private DemoService demoService;

    @GetMapping(value = "list")
    public Object list() {
        return new ResponseData(demoService.findAllDemos());
    }

    @GetMapping(value = "create")
    public Object create() {
        return new ResponseData(demoService.createDemo(100, "no detail"));
    }

}
