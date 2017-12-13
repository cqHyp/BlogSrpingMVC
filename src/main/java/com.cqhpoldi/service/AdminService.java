package com.cqhpoldi.service;

import com.cqhpoldi.dao.AdminDAO;
import com.cqhpoldi.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminService {
    @Autowired
    private AdminDAO adminDAO;

    public Admin addNewAdmin(Admin admin){
        System.out.println("在 service 内了");
        Admin result = adminDAO.addAdmin(admin);
        return result;
    }

}
