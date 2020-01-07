package com.zxhz.enums;

import lombok.Getter;

/**
 * 类描述：
 * 创建作者：gt
 * 创建日期 ： 2019/12/5
 */
@Getter
public enum ResultEnum {
    LOGIN_SUCCESS(1000, "登录成功"),
    USERNAME_NOTEXIST(1001, "用户名不存在"),
    PASSWORD_ERROR(1002, "密码错误"),
    LOCKEDACCOUNT(1003, "被锁了，找管理员解锁去吧！"),
    INSERT_SUCCESS(1004, "增加成功"),
    INSERT_ERROR(1005, "增加失败"),
    SELECT_SUCCESS(1006, "查询成功"),
    SELECT_ERROR(1007, "查询失败"),
    UPDATE_SUCCESS(1008, "修改成功"),
    UPDATE_ERROR(1009, "修改失败"),
    DELETE_SUCCESS(1010, "删除成功"),
    DELETE_ERROR(1011, "删除失败"),
    RECORD_IS_NULL(2002, "未查询到信息"),
    SAVE_FAIL(2004, "保存信息失败"),;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
