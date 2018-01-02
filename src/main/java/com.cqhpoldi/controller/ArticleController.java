package com.cqhpoldi.controller;

import com.cqhpoldi.pojo.Category;
import com.cqhpoldi.pojo.Tags;
import com.cqhpoldi.service.ArticleService;
import com.cqhpoldi.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public Response addNewCategory(@RequestBody Category category, HttpServletRequest request){
        Response response = new Response();
        Object result;
        String token = request.getParameter("token");
        if (articleService.checkToken(token)){
            if (articleService.findByName(category.getName())){
                // 分类已存在 无法添加
                result = false;
                response.setCode(0);
                response.setMessage("分类已存在！");
            }else {
                // 分类不存在 可以添加
                if (articleService.addCategory(category)){
                    // 添加成功
                    result = true;
                    response.setCode(1);
                    response.setMessage("添加分类成功！");
                }else {
                    // 添加失败
                    result = false;
                    response.setCode(0);
                    response.setMessage("添加分类失败！");
                }
            }
        }else {
            result = false;
            response.setCode(0);
            response.setMessage("Token 无效");
        }
        response.setData(result);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/getCategoryParent", method = RequestMethod.POST)
    public Response getCategoryParentList(){
        Response response = new Response();
        Object result;

        List<Category> list = articleService.getCategoryParentList();
        result = list;
        response.setCode(1);
        response.setMessage("获取成功！");

        response.setData(result);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/getCategoryList", method = RequestMethod.POST)
    public Response getCategoryList(@RequestBody Map<String, String> map){
        Response response = new Response();
        Object result;
        int page = Integer.parseInt(map.get("page"));
        int count = Integer.parseInt(map.get("count"));

        List<Category> list;
        int number;
        if (map.containsKey("key")){
            // 是否有关键字 查询
            String key = map.get("key");
            list = articleService.getCategoryList(page, count, key);
            number = articleService.CountCategoryList(key);
        }else {
            list = articleService.getCategoryList(page, count, null);
            number = articleService.CountCategoryList(null);
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("list",list);
        resultMap.put("count",number);

        result = resultMap;
        response.setCode(1);
        response.setMessage("获取成功！");

        response.setData(result);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/addTags", method = RequestMethod.POST)
    public Response addNewTags(@RequestBody Tags tags, HttpServletRequest request){
        Response response = new Response();
        Object result;
        String token = request.getParameter("token");
        if (token == null){
            response.setMessage("Token 不能为空！");
            response.setCode(0);
            result = false;
        }else {
            if (articleService.checkToken(token)){
                if (articleService.addTags(tags)){
                    result = true;
                    response.setCode(1);
                    response.setMessage("添加标签成功！");
                }else {
                    result = false;
                    response.setCode(0);
                    response.setMessage("添加标签失败！");
                }
            }else {
                result = false;
                response.setCode(0);
                response.setMessage("Token 无效");
            }
        }
        response.setData(result);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/getTagsList", method = RequestMethod.POST)
    public Response getTagsList(@RequestBody Map<String,String> map){
        Response response = new Response();
        Object result;
        int page = Integer.parseInt(map.get("page"));
        int count = Integer.parseInt(map.get("count"));

        List<Tags> list;
        int number;
        System.out.println("page = " + page + "count = " + count);

        if (map.containsKey("key")){
            String key = map.get("key");
            list = articleService.getTagsList(page, count, key);
            number = articleService.CountTagsList(key);
        }else {
            list = articleService.getTagsList(page, count, null);
            number = articleService.CountTagsList(null);
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("list",list);
        resultMap.put("count",number);

        result = resultMap;
        response.setCode(1);
        response.setMessage("获取成功！");

        response.setData(result);
        return response;
    }
}
