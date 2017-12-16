package com.cqhpoldi.controller;

import com.cqhpoldi.pojo.Admin;
import com.cqhpoldi.service.AdminService;
import com.cqhpoldi.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody Admin admin){
        Response response = new Response();
        Object result;
        if (adminService.findByName(admin.getName())){
            Admin reAdmin = adminService.login(admin.getName(),admin.getPassword());
            if (reAdmin != null){
                // 登录成功 更新登录信息
                adminService.updateAdmin(reAdmin);
                result = reAdmin;
                response.setCode(1);
                response.setMessage("登陆成功！");
            }else {
                result = false;
                response.setCode(0);
                response.setMessage("密码错误！");
            }
        }else {
            result = false;
            response.setMessage("账号不存在！");
            response.setCode(0);
        }
        response.setData(result);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Response register(@RequestBody Admin admin){
        Response response = new Response();
        Object result;
        if (adminService.findByName(admin.getName())){
            // 用户名 存在 无法注册
            result = false;
            response.setCode(0);
            response.setMessage("用户名已存在！");
        }else {
            // 用户名 不存在 可以注册
            if (adminService.addNewAdmin(admin)){
                // 注册 成功
                result = true;
                response.setCode(1);
                response.setMessage("注册成功！");
            }else {
                // 注册 失败
                result = false;
                response.setCode(0);
                response.setMessage("注册失败！");
            }
        }
        response.setData(result);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public Response resetPassword(@RequestBody Admin admin){
        Response response = new Response();
        Object result = null;
        if (adminService.findByName(admin.getName())){
            Admin reAdmin = adminService.getByName(admin.getName());
            // 用户 存在 可以修改密码
            reAdmin.setPassword(admin.getPassword());
            if (adminService.resetPassword(reAdmin)){
                // 修改成功
                result = reAdmin;
                response.setCode(1);
                response.setMessage("修改成功！");
            }else {
                // 修改失败
                result = false;
                response.setCode(0);
                response.setMessage("修改失败！");
            }
        }else {
            // 用户 不存在 失败
            result = false;
            response.setCode(0);
            response.setMessage("用户不存在！");
        }

        response.setData(result);
        return response;
    }

}
