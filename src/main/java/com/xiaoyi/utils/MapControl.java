package com.xiaoyi.utils;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class MapControl {
    private static  final int SUCCESS_CODE = 200;
    private static  final int PAGE_SUCCESS_CODE = 0;

    private static  final int ERROR_CODE = 500;
    private Map<String,Object> paramMap = Maps.newHashMap();
    private MapControl() {
    }

    public static MapControl getInstance(){
        return new MapControl();
    }
    public Map<String,Object> getMap(){
        return paramMap;
    }
    public MapControl put(Map<String,Object> map){
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            paramMap.put(entry.getKey(),entry.getValue());
        }
        return this;
    }
    public MapControl put(String key, Object val){
        paramMap.put(key,val);
        return this;
    }
    public MapControl add(String key, Object val){
        return this.put(key,val);

    }
    public MapControl success(int code){
        return this.put("msg","操作成功").put("code",code);

    }
    public MapControl success(){
        return this.put("msg","操作成功").put("code",SUCCESS_CODE);

    }
    public MapControl error(){
        return this.put("msg","操作失败").put("code",ERROR_CODE);

    }


    public MapControl success(String str){
        return this.put("msg",str).put("code",SUCCESS_CODE);

    }
    public MapControl page(List<?> data,Integer count){
       return this.put("msg","返回成功").put("code",PAGE_SUCCESS_CODE).put("count",count).put("data",data);

    }
    public MapControl error(String str){
        return this.put("msg",str).put("code",ERROR_CODE);

    }

    public static void main(String[] args) {
        Map<String, Object> map = MapControl.getInstance().put("name", "sgg").put("pwd", "sggh").getMap();
        System.out.println(map);
    }
}
