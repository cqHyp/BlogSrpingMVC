package com.cqhpoldi.service;

import com.cqhpoldi.dao.AdminDAO;
import com.cqhpoldi.pojo.AdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminService {
    @Autowired
    private AdminDAO adminDAO;

    public AdminEntity addNewAdmin(AdminEntity admin){
        System.out.println("在 service 内了");
        AdminEntity adminEntity = adminDAO.addAdmin(admin);
        return adminEntity;
    }

}
