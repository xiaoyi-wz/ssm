package com.xiaoyi.entity;

import java.io.Serializable;
import java.util.Date;

import com.xiaoyi.utils.Entity;
import lombok.Data;

/**
 * tb_answer_opt
 * @author 
 */
@Data
public class AnswerOpt extends Entity {
    private Integer id;

    private Integer surveyId;

    private Integer questionId;

    private Integer optId;

    private Date createTime;

    private String voter;

    /**
     * 单选/多选
     */
    private String type;

    private static final long serialVersionUID = 1L;


}