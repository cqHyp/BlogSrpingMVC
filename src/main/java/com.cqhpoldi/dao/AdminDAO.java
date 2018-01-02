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

    public Admin login(String name, String password){
        // 根据 name 和 password 找 Admin

        String hql = "from Admin where name = :name and password = :password";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("name",name);
        query.setString("password",password);

        List<Admin> list = query.list();


        if (list != null && list.size() > 0){
            return list.get(0);
        }else {
            return null;
        }
    }

    public boolean findByName(String name){
        // 根据 name 找 Admin

        String hql = "from Admin where name = :name";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("name", name);

        List<Admin> list = query.list();

        if (list != null && list.size() > 0){
            return true;
        }else {
            return false;
        }
    }

    public Admin getByName(String name){
        // 根据 name 找 Admin

        String hql = "from Admin where name = :name";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("name", name);

        List<Admin> list = query.list();


        if (list != null && list.size() > 0){
            return list.get(0);
        }else {
            return null;
        }
    }

    public boolean addAdmin(Admin admin) {
        try{
            sessionFactory.getCurrentSession().save(admin);
            return true;
        }catch (HibernateException e){
            return false;
        }

    }

    public boolean update(Admin admin){

        try{
            sessionFactory.getCurrentSession().saveOrUpdate(admin);
            return true;
        }catch (HibernateException e){
            return false;
        }
    }

    public Admin findByToken(String token){

        String hql = "from Admin where token = :token";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("token",token);

        List<Admin> list = query.list();

        if (list != null && list.size() > 0){
            return list.get(0);
        }else {
            return null;
        }
    }

    public boolean checkToken(String token){
        String hql = "from Admin where token = :token";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("token",token);

        List<Admin> list = query.list();

        if (list != null && list.size() > 0){
            return true;
        }else {
            return false;
        }
    }
}
