package com.cqhpoldi.service;

import com.cqhpoldi.dao.AdminDAO;
import com.cqhpoldi.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Service("adminService")
public class AdminService {
    @Autowired
    private AdminDAO adminDAO;

    public Admin addNewAdmin(Admin admin){
        System.out.println("在 service 内了");
        admin.setAvatar("avatar");

        return adminDAO.addAdmin(admin);
    }

    public Admin updateAdmin(){
        return adminDAO.update();
    }

}
