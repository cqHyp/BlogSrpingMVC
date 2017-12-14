package com.cqhpoldi.dao;

import com.cqhpoldi.dao.DAOImp.AdminDAOImp;
import com.cqhpoldi.pojo.Admin;
import com.cqhpoldi.pojo.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("adminDAO")
public class AdminDAO implements AdminDAOImp {

    Configuration cfg = new Configuration().configure();

    @Autowired
    private SessionFactory sessionFactory;

    Session session = null;

    @Transactional
    public Admin addAdmin(Admin admin) {
        sessionFactory.getCurrentSession().save(admin);
        return admin;
    }

    public Admin update(){
        String hql = "from Admin where name = 'cqh'";
        List<Admin> list = sessionFactory.openSession().createQuery(hql).list();

        return list.get(0);
    }
}
