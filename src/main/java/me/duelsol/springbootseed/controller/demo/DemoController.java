package me.duelsol.springbootseed.controller.demo;

import lombok.extern.slf4j.Slf4j;
import me.duelsol.springbootseed.framework.security.AccessTokenManager;
import me.duelsol.springbootseed.framework.support.ResponseData;
import me.duelsol.springbootseed.service.demo.DemoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 冯奕骅
 */
@RestController
@Slf4j
public class DemoController {

    @Autowired
    private DemoService demoService;

    @PostMapping(value = "login")
    public ResponseData login() {
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return new ResponseData(AccessTokenManager.generate());
    }

    @PostMapping(value = "logout")
    public ResponseData logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ResponseData("Logout successful.");
    }

    @GetMapping(value = "list")
    public ResponseData list() {
        return new ResponseData(demoService.findAllDemos());
    }

    @PostMapping(value = "create")
    public ResponseData create() {
        return new ResponseData(demoService.createDemo(100, "no detail"));
    }

}
