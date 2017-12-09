package com.cqhpoldi.dao;

import com.cqhpoldi.dao.DAOImp.AdminDAOImp;
import com.cqhpoldi.pojo.AdminEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("adminDAO")
public class AdminDAO extends HibernateDaoSupport implements AdminDAOImp {

    @Autowired
    private SessionFactory sessionFactory;

    public AdminEntity addAdmin(AdminEntity admin) {
        admin.setAvatar("avatar");
        sessionFactory.getCurrentSession().save(admin);
        return admin;
    }
}
