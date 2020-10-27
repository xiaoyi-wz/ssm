
package com.xiaoyi.utils;

import java.util.*;

import com.google.common.base.CaseFormat;
import com.xiaoyi.entity.Admin;
import com.xiaoyi.entity.Question;
import com.xiaoyi.entity.QuestionOpt;
import com.xiaoyi.entity.Survey;
import com.xiaoyi.service.QuestionOptService;
import org.springframework.cglib.beans.BeanMap;

public class BeanMapUtils
{
    public static <T> Map<String, Object> beanToMap(T bean)
    {
        Map<String, Object> map = new HashMap();
        BeanMap beanMap;
        if (bean != null)
        {
            beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }
    public static String upperFirstLatter(String letter){
        char[] chars =  letter.toCharArray();
        if(chars[0] <= 'z' && chars[0]>='a'){
            chars[0] = (char) (chars[0]-32);
        }
        return new String(chars);
    }
    public static <T> Map<String, Object> beanToMapForUpdate(T bean)
    {
        Map<String, Object> map = new HashMap();
        BeanMap beanMap;
        if (bean != null)
        {
            beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
//                map.put(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,"update_"+key),beanMap.get(key));
                map.put("update"+upperFirstLatter(key+""),beanMap.get(key));
            }
        }
        return map;
    }

    public static <T> T mapToBean(Map<String, Object> map, T bean)
    {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList)
    {
        List<Map<String, Object>> list = new ArrayList();
        if ((objList != null) && (objList.size() > 0))
        {
            Map<String, Object> map = null;
            T bean = null;
            int i = 0;
            for (int size = objList.size(); i < size; i++)
            {
                bean = objList.get(i);
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz)
            throws InstantiationException, IllegalAccessException
    {
        List<T> list = new ArrayList();
        if ((maps != null) && (maps.size() > 0))
        {
            Map<String, Object> map = null;
            T bean = null;
            int i = 0;
            for (int size = maps.size(); i < size; i++)
            {
                map = (Map)maps.get(i);
                bean = clazz.newInstance();
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;

    }

    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.setId(1);
        admin.setAccount("wz");
        admin.setRemark("是我");
        Map<String,Object> map = beanToMap(admin);
        System.out.println(map);

        Map<String,Object> map2 = beanToMapForUpdate(admin);
        System.out.println(map2);

        Survey survey = new Survey();
        survey.setEndTime(new Date());
        Map<String,Object> map3 = beanToMapForUpdate(survey);
        System.out.println(map3);

        QuestionOpt questionOpt = new QuestionOpt();




    }
}
 