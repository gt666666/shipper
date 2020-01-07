package com.zxhz.enums;

import lombok.Getter;

@Getter
public enum TableNameEnum {
    CONTRACT_INFO(5013, "contract_info");
    
    private Integer code;
    private String message;

    TableNameEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}