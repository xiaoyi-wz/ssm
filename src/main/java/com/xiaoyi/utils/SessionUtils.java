package com.xiaoyi.utils;

import com.xiaoyi.entity.Admin;

import javax.servlet.http.HttpServletRequest;

public class SessionUtils {
    private static final String key = "admin";
    public static void setAdmin(HttpServletRequest request, Admin admin){
        request.getSession().setAttribute(key,admin);

    }
    public static Admin getAdmin(HttpServletRequest request){
        return (Admin) request.getSession().getAttribute(key);
    }
}
