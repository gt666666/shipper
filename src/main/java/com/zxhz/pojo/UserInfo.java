package com.zxhz.pojo;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.org.apache.bcel.internal.generic.LineNumberGen;
import com.zxhz.config.convert.CustomDateChange;
import com.zxhz.config.convert.CustomTimeChange;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 类描述：
 * 创建作者：gt
 * 创建日期 ： 2019/12/11
 */
@Data
public class UserInfo implements Serializable {
    private int id;
    private String name;
    @JsonSerialize(using = CustomDateChange.class)
    private Date birthday;
    private String city;
    private Integer age;
    private String gender;
    private Boolean isLock;
    private Date createDate;
   @JsonSerialize(using = CustomTimeChange.class)
    private Date everyTip;
    private BigDecimal money;
    private String img;
    private String hobby;
    private String[] hobbys;
    private String remark;
    private String education;

}
