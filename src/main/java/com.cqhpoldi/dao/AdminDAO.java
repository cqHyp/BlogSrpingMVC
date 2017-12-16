package com.cqhpoldi.dao;

import com.cqhpoldi.dao.DAOImp.AdminDAOImp;
import com.cqhpoldi.pojo.Admin;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.List;

@Repository("adminDAO")
@Transactional
public class AdminDAO implements AdminDAOImp {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    Session session = null;

    @Transactional
    public Admin login(String name, String password){
        // 根据 name 和 password 找 Admin
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Admin where name = :name and password = :password";
        Query query = session.createQuery(hql);
        query.setString("name",name);
        query.setString("password",password);

        List<Admin> list = query.list();

        transaction.commit();
        session.close();

        if (list.size() > 0 && list != null){
            return list.get(0);
        }else {
            return null;
        }
    }

    @Transactional
    public boolean findByName(String name){
        // 根据 name 找 Admin
        session = sessionFactory.getCurrentSession();
        Transaction transactional = session.beginTransaction();

        String hql = "from Admin where name = :name";
        Query query = session.createQuery(hql);
        query.setString("name", name);

        List<Admin> list = query.list();

        transactional.commit();

        if (list.size() > 0 && list != null){
            return true;
        }else {
            return false;
        }
    }

    @Transactional
    public Admin getByName(String name){
        // 根据 name 找 Admin
        session = sessionFactory.getCurrentSession();
        Transaction transactional = session.beginTransaction();

        String hql = "from Admin where name = :name";
        Query query = session.createQuery(hql);
        query.setString("name", name);

        List<Admin> list = query.list();

        transactional.commit();

        if (list.size() > 0 && list != null){
            return list.get(0);
        }else {
            return null;
        }
    }

    @Transactional
    public boolean addAdmin(Admin admin) {
        // 获取session
        session = sessionFactory.getCurrentSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();

        // 事务操作
        try{
            sessionFactory.getCurrentSession().save(admin);
            transaction.commit();
            return true;
        }catch (HibernateException e){
            return false;
        }
    }

    @Transactional
    public boolean update(Admin admin){
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try{
            session.saveOrUpdate(admin);
            transaction.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            session.close();
            return false;
        }
    }
}
