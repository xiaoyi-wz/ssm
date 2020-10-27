package com.xiaoyi.controller;

import com.google.common.base.Strings;
import com.xiaoyi.entity.Admin;
import com.xiaoyi.service.AdminService;
import com.xiaoyi.utils.MD5Utils;
import com.xiaoyi.utils.MapControl;
import com.xiaoyi.utils.MapParameter;
import com.xiaoyi.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller

public class LoginController {
    @Autowired
    private AdminService adminService;
    @GetMapping("/login")
    public String v_login(){
        return "login";
    }
    @PostMapping("/login")
    @ResponseBody
    public Map<String,Object> login(@RequestBody Map<String,Object> map, HttpServletRequest request){
        String account = map.get("account")+"";
        String password =map.get("password")+"";
        if(Strings.isNullOrEmpty(account)|| Strings.isNullOrEmpty(password)){
            return MapControl.getInstance().error("用户名或密码不为空").getMap();
        }
        Admin admin = adminService.login(account, MD5Utils.getMD5(password));
        if(admin != null){
            SessionUtils.setAdmin(request,admin);
            return MapControl.getInstance().success("登录成功").getMap();
        }else {
            return MapControl.getInstance().error("用户名或密码密码错误").getMap();
        }


    }
}
