package com.cqhpoldi.dao;

import com.cqhpoldi.dao.DAOImp.TagsDAOImp;
import com.cqhpoldi.pojo.Tags;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.swing.text.html.HTML;
import java.util.List;

@Repository("tagsDAO")
@Transactional
public class TagsDAO implements TagsDAOImp {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public boolean addNewTags(Tags tags) {
        try {
            sessionFactory.getCurrentSession().save(tags);
            return true;
        }catch (HibernateException e){
            return false;
        }
    }

    public List<Tags> findTagsList(int page, int count, String key) {
        String hql = "from Tags where name like '%" + key + "%'";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setFirstResult(page * count);
        query.setMaxResults(count);

        List<Tags> list = query.list();
        return list;
    }

    public int CountTagsList(String key){
        String hql = "select count (*) from Tags where name like '%" + key + "%'";
        Long count = (Long) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
        return Integer.parseInt(String.valueOf(count));
    }
}
