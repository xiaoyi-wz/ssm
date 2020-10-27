package com.xiaoyi.controller;

import com.xiaoyi.entity.Admin;
import com.xiaoyi.entity.QuestionOpt;

import com.xiaoyi.service.QuestionOptService;

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
@RequestMapping("/questionOpt")
public class QuestionOptController {
    @Autowired
    private QuestionOptService questionOptService;

    @GetMapping("/create")
    public String v_create(){
        return "questionOpt/add";
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody QuestionOpt questionOpt, HttpServletRequest request){
        Admin curAdmin = SessionUtils.getAdmin(request);

//        questionOpt.setCreator(curAdmin.getId());
//        System.out.println(questionOpt.getCreateTime());
//        questionOpt.setCreateTime(questionOpt.getCreateTime());
        questionOpt.setAnswer(questionOpt.getAnswer()!=null?"yes":"");

        int result = questionOptService.create(questionOpt);
        System.out.println(result);
        if(result< 0){
            return MapControl.getInstance().error().getMap();

        }
        return MapControl.getInstance().success().getMap();

    }
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids){
        int result = questionOptService.deleteBatch(ids);
        if(result< 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }
    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(@RequestBody QuestionOpt questionOpt){
        questionOpt.setAnswer(questionOpt.getAnswer()!=null?"yes":"");
        int result = questionOptService.update(questionOpt);
        if(result< 0){
            return MapControl.getInstance().error().getMap();

        }
        return MapControl.getInstance().success().getMap();

    }
    @GetMapping("/list")
    public String list(){
        return "questionOpt/list";
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(@RequestBody QuestionOpt questionOpt){
        System.out.println(questionOpt.getPage());
        System.out.println(questionOpt.getLimit());
        List<QuestionOpt> list = questionOptService.query(questionOpt);
        System.out.println(list);
        Integer count = questionOptService.count(questionOpt);
        return MapControl.getInstance().page(list,count).getMap();

    }
    @GetMapping("/detail")
    public String detail(Integer id, ModelMap modelMap){
        QuestionOpt questionOpt = questionOptService.detail(id);
        modelMap.addAttribute("questionOpt",questionOpt);
        return "questionOpt/update";

    }


}
