package com.cqhpoldi.dao;

import com.cqhpoldi.dao.DAOImp.CategoryDAOImp;
import com.cqhpoldi.pojo.Category;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Repository("categoryDAO")
@Transactional
public class CategoryDAO implements CategoryDAOImp {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    Session session = null;

    @Transactional
    public boolean addCategory(Category category) {
        session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();// 开启事务

        try{
            session.save(category);
            transaction.commit();
            return true;
        }catch (HibernateException e){
            return false;
        }
    }
}
