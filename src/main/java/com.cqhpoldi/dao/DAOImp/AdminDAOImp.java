package com.cqhpoldi.dao.DAOImp;

import com.cqhpoldi.pojo.Admin;

public interface AdminDAOImp {
    // 管理员注册
    boolean addAdmin(Admin admin);
    // 登录 根据 name 与 password 获取 info
    Admin login(String name, String password);
    // 根据 name 判断是否存在该管理员
    boolean findByName(String name);
    // 更新 Admin
    boolean update(Admin admin);
}
