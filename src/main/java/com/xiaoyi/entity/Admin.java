package com.xiaoyi.entity;

import com.xiaoyi.utils.Entity;
import lombok.Data;

import java.io.Serializable;

@Data
public class Admin extends Entity {
    private Integer id;
    private String account;
    private String password;
    private String name;
    private String phone;
    private String remark;


}
