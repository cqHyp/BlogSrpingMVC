package com.cqhpoldi.service;

import com.cqhpoldi.dao.CategoryDAO;
import com.cqhpoldi.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("articleService")
public class ArticleService {
    @Autowired
    private CategoryDAO categoryDAO;

    @Transactional
    public boolean addCategory(Category category){
        return categoryDAO.addCategory(category);
    }
}
