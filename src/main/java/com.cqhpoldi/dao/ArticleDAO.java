package com.cqhpoldi.dao;

import com.cqhpoldi.dao.DAOImp.ArticleDAOImp;
import com.cqhpoldi.pojo.Article;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Repository("articleDAO")
@Transactional
public class ArticleDAO implements ArticleDAOImp {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }


    public boolean addArticle(Article article) {
        try{
            sessionFactory.getCurrentSession().save(article);
            return true;
        }catch (HibernateException e){
            return false;
        }
    }
}
