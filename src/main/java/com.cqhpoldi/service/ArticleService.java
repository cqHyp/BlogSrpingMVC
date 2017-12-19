package com.cqhpoldi.service;

import com.cqhpoldi.dao.CategoryDAO;
import com.cqhpoldi.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("articleService")
public class ArticleService {
    @Autowired
    private CategoryDAO categoryDAO;

    public boolean addCategory(Category category){
        return categoryDAO.addCategory(category);
    }

    public boolean findByName(String name){
        return categoryDAO.findByName(name);
    }

    public List<Category> getCategoryParentList(){
        return categoryDAO.getCategoryList();
    }

    public List<Category> getCategoryList(int page, int count, String name){
        return categoryDAO.getCategoryList(page, count, name);
    }

    public int CountCategoryList(String key){
        return categoryDAO.CountCategoryList(key);
    }
}
