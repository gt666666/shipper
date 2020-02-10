package com.zxhz.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 类描述：
 * 创建作者：gt
 * 创建日期 ： 2019/11/19
 */
@Data
public class Member implements Serializable {
    private String mid;
    private String password;
    private String name;
    private String phone;
    private Integer locked;
    private Date createDate;
}
