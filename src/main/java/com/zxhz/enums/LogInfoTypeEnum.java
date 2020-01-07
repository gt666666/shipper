package com.zxhz.enums;

import lombok.Getter;

/**
 * 日志类型枚举
 *
 * @author lixingwu
 */
@Getter
public enum LogInfoTypeEnum {
    /****日志类型枚举*/
    INSERT(1, "添加"),
    UPDATE(2, "修改"),
    DELETE(3, "删除"),
    STATUS(4, "状态修改");

    private Integer code;
    private String message;

    LogInfoTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
