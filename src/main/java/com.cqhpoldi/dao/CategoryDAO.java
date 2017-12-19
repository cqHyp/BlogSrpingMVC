package com.cqhpoldi.dao;

import com.cqhpoldi.dao.DAOImp.CategoryDAOImp;
import com.cqhpoldi.pojo.Category;
import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Repository("categoryDAO")
@Transactional
public class CategoryDAO implements CategoryDAOImp {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }


    public boolean addCategory(Category category) {
        try{
            sessionFactory.getCurrentSession().save(category);
            return true;
        }catch (HibernateException e){
            return false;
        }
    }

    public List<Category> getCategoryList(){
        List<Category> list = sessionFactory.getCurrentSession().createCriteria(Category.class).list();
        return list;
    }

    public List<Category> getCategoryList(int page, int count, String name){
        String hql = "from Category where name like '%" + name + "%'";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setFirstResult(page * count);
        query.setMaxResults(count);

        List<Category> list = query.list();
        return list;
    }

    public int CountCategoryList(String key){
        String hql = "select count (*) from Category where name like '%" + key + "%'";
        Long count = (Long) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
        return Integer.parseInt(String.valueOf(count));
    }

    public boolean findByName(String name){
        String hql = "from Category where name = :name";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("name", name);
        List<Category> list = query.list();
        if (list != null && list.size() > 0){
            return true;
        }else {
            return false;
        }
    }
}
