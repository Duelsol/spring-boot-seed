package me.duelsol.springbootseed.controller.demo;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import me.duelsol.springbootseed.framework.security.AccessTokenManager;
import me.duelsol.springbootseed.framework.support.ResponseData;
import me.duelsol.springbootseed.service.demo.IDemoService;
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
    private IDemoService demoService;

    @Operation(summary = "登录接口", description = "", tags = { "demo" })
    @PostMapping(value = "login")
    public String login() {
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return AccessTokenManager.generate();
    }

    @Operation(summary = "注销接口", description = "", tags = { "demo" })
    @PostMapping(value = "logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "Logout successful.";
    }

    @Operation(summary = "列表接口", description = "获取所有数据", tags = { "demo" })
    @GetMapping(value = "list")
    public ResponseData list() {
        return ResponseData.success(demoService.findAllDemos());
    }

    @Operation(summary = "保存接口", description = "新增数据", tags = { "demo" })
    @PostMapping(value = "save")
    public ResponseData save() {
        return ResponseData.success(demoService.createDemo(100, "no detail"));
    }

}
