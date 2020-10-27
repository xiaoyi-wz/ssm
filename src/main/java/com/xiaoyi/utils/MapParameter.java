package com.xiaoyi.utils;

import com.google.common.collect.Maps;

import java.util.Map;

public class MapParameter {
    private Map<String,Object> paramMap = Maps.newHashMap();
    private MapParameter() {
    }

    public static MapParameter getInstance(){
        return new MapParameter();
    }
    public Map<String,Object> getMap(){
        return paramMap;
    }
    public MapParameter put( Map<String,Object> map){
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            paramMap.put(entry.getKey(),entry.getValue());
        }
        return this;
    }
    public MapParameter put(String key,Object val){
        paramMap.put(key,val);
        return this;
    }
    public MapParameter add(String key,Object val){
        return this.put(key,val);

    }
    public MapParameter addId(Object val){
        return this.put("id",val);

    }

    public static void main(String[] args) {
        Map<String, Object> map = MapParameter.getInstance().put("name", "sgg").put("pwd", "sggh").getMap();
        System.out.println(map);
    }
}
