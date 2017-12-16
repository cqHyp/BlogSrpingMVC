package com.cqhpoldi.service;

import com.cqhpoldi.dao.AdminDAO;
import com.cqhpoldi.pojo.Admin;
import com.cqhpoldi.util.MD5Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Service("adminService")
public class AdminService {
    @Autowired
    private AdminDAO adminDAO;

    @Transactional
    public Admin login(String name, String password){
        return adminDAO.login(name,password);
    }

    @Transactional
    public boolean findByName(String name){
        return adminDAO.findByName(name);
    }

    @Transactional
    public Admin getByName(String name){
        return adminDAO.getByName(name);
    }

    @Transactional
    public boolean addNewAdmin(Admin admin){
        //注册 创建 createTime
        Timestamp timestamp = new Timestamp((new Date()).getTime());
        admin.setCreateTime(timestamp);
        return adminDAO.addAdmin(admin);
    }

    @Transactional
    public boolean updateAdmin(Admin admin){
        // 更新 近期登录时间
        Timestamp timestamp = new Timestamp((new Date()).getTime());
        admin.setLastLoginTime(timestamp);
        // 更新生成 token
        String token = MD5Tools.MD5(admin.getPassword() + admin.getLastLoginTime() + admin.getName());
        admin.setToken(token);
        return adminDAO.update(admin);
    }

    @Transactional
    public boolean resetPassword(Admin admin){
        // 更新 最近一次修改时间
        Timestamp timestamp = new Timestamp((new Date()).getTime());
        admin.setChangeTime(timestamp);

        return adminDAO.update(admin);
    }

}
