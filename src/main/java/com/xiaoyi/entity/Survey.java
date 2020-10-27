package com.xiaoyi.entity;

import java.io.Serializable;
import java.util.Date;

import com.xiaoyi.utils.Entity;
import lombok.Data;

/**
 * tb_survey
 * @author 
 */
@Data
public class Survey extends Entity {
    public static final String state_create="创建";
    public static final String state_exe="执行中";
    public static final String state_over="结束";
    private Integer id;

    private String title;

    private String remark;

    /**
     * 0:限制 1： 不限制
     */
    private Integer bounds;

    private Date startTime;

    private Date endTime;

    /**
     * 0:公开 1：密码
     */
    private Integer rules;

    private String password;

    private String url;

    /**
     * 创建、执行中、结束
     */
    private String state;

    private String logo;

    private String bgimg;

    /**
     * 0:匿名 1：不匿名
     */
    private Integer anon;

    private Integer creator;

    private Date createTime;

    private static final long serialVersionUID = 1L;


}