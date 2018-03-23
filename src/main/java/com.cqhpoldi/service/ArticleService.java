package com.cqhpoldi.service;

import com.cqhpoldi.dao.AdminDAO;
import com.cqhpoldi.dao.ArticleDAO;
import com.cqhpoldi.dao.CategoryDAO;
import com.cqhpoldi.dao.TagsDAO;
import com.cqhpoldi.pojo.Article;
import com.cqhpoldi.pojo.Category;
import com.cqhpoldi.pojo.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleService")
public class ArticleService {
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private AdminDAO adminDAO;
    @Autowired
    private TagsDAO tagsDAO;
    @Autowired
    private ArticleDAO articleDAO;

    // 验证 token
    public boolean checkToken(String token){
        return adminDAO.checkToken(token);
    }

    // 新增 Category 分类
    public boolean addCategory(Category category){
        return categoryDAO.addCategory(category);
    }

    // 验证 Category 分类 名称是否存在
    public boolean findByName(String name){
        return categoryDAO.findByName(name);
    }

    // 获取 Category 分类 父类 列表
    public List<Category> getCategoryParentList(){
        return categoryDAO.getCategoryList();
    }

    // 获取 Category 分类列表
    public List<Category> getCategoryList(int page, int count, String name){
        return categoryDAO.getCategoryList(page, count, name);
    }

    // 计算 获取 Category 列表 的数量
    public int CountCategoryList(String key){
        return categoryDAO.CountCategoryList(key);
    }

    // 新增 tags 标签
    public boolean addTags(Tags tags){
        return tagsDAO.addNewTags(tags);
    }

    // 获取 tags 标签列表
    public List<Tags> getTagsList(int page, int count, String key){
        return tagsDAO.findTagsList(page, count, key);
    }

    // 计算 获取 Tags 列表 的数量
    public int CountTagsList(String key){
        return tagsDAO.CountTagsList(key);
    }

    // 新增 博客
    public boolean addArticle(Article article){
        return articleDAO.addArticle(article);
    }
}
