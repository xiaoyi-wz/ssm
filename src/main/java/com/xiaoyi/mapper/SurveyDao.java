package com.xiaoyi.mapper;

import com.xiaoyi.entity.AnswerOpt;
import com.xiaoyi.entity.Survey;

import java.util.List;
import java.util.Map;

public interface SurveyDao {
    public int create(Survey survey);
    public int delete(Map<String,Object> paramMap);
    public int update(Map<String,Object> paramMap);
    public List<Survey> query(Map<String,Object> paramMap);
    public Survey detail(Map<String,Object> paramMap);
    public int count(Map<String,Object> paramMap);
}