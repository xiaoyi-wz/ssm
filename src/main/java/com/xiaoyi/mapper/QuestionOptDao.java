package com.xiaoyi.mapper;

import com.xiaoyi.entity.AnswerOpt;
import com.xiaoyi.entity.Question;
import com.xiaoyi.entity.QuestionOpt;

import java.util.List;
import java.util.Map;

public interface QuestionOptDao {
    public int create(QuestionOpt questionOpt);
    public int delete(Map<String,Object> paramMap);
    public int update(Map<String,Object> paramMap);
    public List<QuestionOpt> query(Map<String,Object> paramMap);
    public QuestionOpt detail(Map<String,Object> paramMap);
    public int count(Map<String,Object> paramMap);
}