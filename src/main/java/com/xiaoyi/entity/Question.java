package com.xiaoyi.entity;

import java.io.Serializable;
import java.util.Date;

import com.xiaoyi.utils.Entity;
import lombok.Data;

/**
 * tb_question
 * @author 
 */
@Data
public class Question extends Entity {
    private Integer id;

    private String title;

    private String remark;

    /**
     * 1单选 2 多选 3单行 4多行
     */
    private Integer type;

    /**
     * 0 非必填 1 必填
     */
    private Integer required;

    /**
     * text,number,date
     */
    private String checkStyle;

    /**
     * 0:顺序1：随机
     */
    private Integer orderStyle;

    /**
     * 1;2;3;4
     */
    private Integer showStyle;

    /**
     * 0:不测评 1 测评
     */
    private Integer test;

    private Integer score;

    private Integer orderby;

    private Integer creator;

    private Date createTime;

    private Integer surveyId;


}