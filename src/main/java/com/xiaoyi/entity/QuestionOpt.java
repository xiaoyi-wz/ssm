package com.xiaoyi.entity;

import java.io.Serializable;

import com.xiaoyi.utils.Entity;
import lombok.Data;

/**
 * tb_question_opt
 * @author 
 */
@Data
public class QuestionOpt extends Entity {
    private Integer id;

    private Integer surveyId;

    private Integer questionId;

    private String opt;

    private Integer orderby;

    /**
     * 默认为null，1：是答案
     */
    private String answer;

    /**
     * 单选|多选
     */
    private Integer type;

    private static final long serialVersionUID = 1L;


}