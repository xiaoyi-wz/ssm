package com.xiaoyi.controller;

import com.xiaoyi.entity.Admin;
import com.xiaoyi.entity.Question;
import com.xiaoyi.service.QuestionService;

import com.xiaoyi.utils.MapControl;
import com.xiaoyi.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/create")
    public String v_create(){
        return "question/add";
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody Question question, HttpServletRequest request){
        Admin curAdmin = SessionUtils.getAdmin(request);

        question.setCreator(curAdmin.getId());
//        System.out.println(question.getCreateTime());
//        question.setCreateTime(question.getCreateTime());

        int result = questionService.create(question);
        System.out.println(result);
        if(result< 0){
            return MapControl.getInstance().error().getMap();

        }
        return MapControl.getInstance().success().getMap();

    }
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids){
        int result = questionService.deleteBatch(ids);
        if(result< 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }
    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(@RequestBody Question question){
//        question.setAnon(question.getAnon()!=null?0:1);
        int result = questionService.update(question);
        if(result< 0){
            return MapControl.getInstance().error().getMap();

        }
        return MapControl.getInstance().success().getMap();

    }
    @GetMapping("/list")
    public String list(){
        return "question/list";
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(@RequestBody Question question){
        System.out.println(question.getPage());
        System.out.println(question.getLimit());
        List<Question> list = questionService.query(question);
        Integer count = questionService.count(question);
        return MapControl.getInstance().page(list,count).getMap();

    }
    @GetMapping("/detail")
    public String detail(Integer id, ModelMap modelMap){
        Question question = questionService.detail(id);
        modelMap.addAttribute("question",question);
        return "question/update";

    }


}
