package com.xiaoyi.controller;

import com.xiaoyi.entity.Admin;
import com.xiaoyi.entity.AnswerOpt;
import com.xiaoyi.service.AnswerOptService;

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
@RequestMapping("/answerOpt")
public class AnswerOptController {
    @Autowired
    private AnswerOptService answerOptService;

    @GetMapping("/create")
    public String v_create(){
        return "answerOpt/add";
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody AnswerOpt answerOpt, HttpServletRequest request){
        Admin curAdmin = SessionUtils.getAdmin(request);

//        answerOpt.setCreator(curAdmin.getId());
//        System.out.println(answerOpt.getCreateTime());
//        answerOpt.setCreateTime(answerOpt.getCreateTime());

        int result = answerOptService.create(answerOpt);
        System.out.println(result);
        if(result< 0){
            return MapControl.getInstance().error().getMap();

        }
        return MapControl.getInstance().success().getMap();

    }
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids){
        int result = answerOptService.deleteBatch(ids);
        if(result< 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }
    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(@RequestBody AnswerOpt answerOpt){
//        answerOpt.setAnon(answerOpt.getAnon()!=null?0:1);
        int result = answerOptService.update(answerOpt);
        if(result< 0){
            return MapControl.getInstance().error().getMap();

        }
        return MapControl.getInstance().success().getMap();

    }
    @GetMapping("/list")
    public String list(){
        return "answerOpt/list";
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(@RequestBody AnswerOpt answerOpt){
        System.out.println(answerOpt.getPage());
        System.out.println(answerOpt.getLimit());
        List<AnswerOpt> list = answerOptService.query(answerOpt);
        Integer count = answerOptService.count(answerOpt);
        return MapControl.getInstance().page(list,count).getMap();

    }
    @GetMapping("/detail")
    public String detail(Integer id, ModelMap modelMap){
        AnswerOpt answerOpt = answerOptService.detail(id);
        modelMap.addAttribute("answerOpt",answerOpt);
        return "answerOpt/update";

    }


}
