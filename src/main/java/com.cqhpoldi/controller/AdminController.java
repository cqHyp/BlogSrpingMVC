package com.cqhpoldi.controller;

import com.cqhpoldi.pojo.AdminEntity;
import com.cqhpoldi.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public AdminEntity register(String username, String password){
        AdminEntity admin = new AdminEntity();
        admin.setName("kaka");
        admin.setPassword("123456");
        System.out.println("service return");
        return adminService.addNewAdmin(admin);
    }

}
