package com.zxhz.config.global;

import com.zxhz.enums.ResultEnum;
public class FailException extends RuntimeException {
    private Integer code;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public FailException() {
        super();
    }

    public FailException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
    public FailException(ResultEnum resultEnum, Object data) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
        this.data = data;
    }
}