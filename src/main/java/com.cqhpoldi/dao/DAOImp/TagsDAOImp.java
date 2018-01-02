package com.cqhpoldi.dao.DAOImp;

import com.cqhpoldi.pojo.Tags;

import java.util.List;

public interface TagsDAOImp {
    // 新增 tags 博客标签
    boolean addNewTags(Tags tags);
    // 查询 tags 博客标签 列表
    List<Tags> findTagsList(int page, int count, String key);
    // 计算 tags 博客标签 列表 的数量
    int CountTagsList(String key);

}
