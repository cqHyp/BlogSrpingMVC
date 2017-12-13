package com.cqhpoldi.dao;

import com.cqhpoldi.dao.DAOImp.AdminDAOImp;
import com.cqhpoldi.pojo.Admin;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("adminDAO")
public class AdminDAO implements AdminDAOImp {

    @Autowired
    private SessionFactory sessionFactory;

    public Admin addAdmin(Admin admin) {
        Session session = null;
        List<Admin> list = null;
        try {
            //实例化Configuration，这行代码默认加载hibernate.cfg.xml文件
            Configuration conf = new Configuration().configure();
            //以Configuration创建SessionFactory
            SessionFactory sf = conf.buildSessionFactory();
            //实例化Session
            session = sf.openSession();
            String hql = "from Admin";
            Query query = session.createQuery(hql);
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return list.get(0);
    }
}
