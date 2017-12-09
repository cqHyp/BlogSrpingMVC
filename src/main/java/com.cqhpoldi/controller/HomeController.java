package com.cqhpoldi.controller;

import com.cqhpoldi.pojo.AdminEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    // 添加一个日志器
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    // 映射一个action
    @RequestMapping("/index")
    public String index(){
        System.out.println("show index");
        logger.info("hello world");
        AdminEntity admin = new AdminEntity();
        admin.setName("cqh");
        admin.setPassword("123456");
        System.out.println(admin.getName() + "11111controller name");
        return "index";
    }



}
