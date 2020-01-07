package com.zxhz.pojo;

import com.zxhz.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述：
 * 创建作者：gt
 * 创建日期 ： 2019/12/5
 */
@Data
public class CommonResult implements Serializable {
    public static final int SUCCESS = 0;
    public static final int FAILS = 1;
    public static final int SERVER_ERROR = -1;
    private int code;
    private String msg;
    private Object data;

    public CommonResult() {
        this.code = FAILS;
        this.msg="";
        this.data = null;
    }

    public CommonResult(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
        this.data = null;
    }

    public CommonResult(ResultEnum resultEnum, List<Object> objectList) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
        this.data = objectList;
    }
}
