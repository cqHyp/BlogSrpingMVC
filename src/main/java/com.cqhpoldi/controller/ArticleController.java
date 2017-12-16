package com.cqhpoldi.controller;

import com.cqhpoldi.pojo.Category;
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

@Controller
@RequestMapping("/article")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public Response addNewCategory(@RequestBody Category category){
        Response response = new Response();
        Object result = null;

        if (articleService.addCategory(category)){
            result = true;
            response.setCode(1);
            response.setMessage("添加分类成功！");
        }else {
            result = false;
            response.setCode(0);
            response.setMessage("添加分类失败！");
        }

        response.setData(result);
        return response;
    }
}
