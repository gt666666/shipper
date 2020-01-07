package com.zxhz.utils;

import cn.hutool.core.date.DateUtil;

import com.zxhz.enums.ResultEnum;
import lombok.Data;
import java.io.Serializable;
import java.util.List;
@Data
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 8656597559014685635L;
    private List<T> data = null;
    private String msg = "";
    private Long count = 0L;
    private Integer code = 0;


    public Page(ResultEnum resultEnum) {
        this.msg = resultEnum.getMessage();
        this.code = resultEnum.getCode();
    }


    public Page(List<T> data, Long count, ResultEnum resultEnum) {
        this.data = data;
        this.count = count;
        this.msg = resultEnum.getMessage();
    }

    public Page(List<T> data) {
        if (data instanceof com.github.pagehelper.Page) {
            com.github.pagehelper.Page<T> page = (com.github.pagehelper.Page<T>) data;
            this.msg = ResultEnum.SELECT_SUCCESS.getMessage();
            this.count = page.getTotal();
            this.data = page;
        }
    }

    /**
     * 返回服务器时间
     */
    public Long getTimestamp() {
        return DateUtil.currentSeconds();
    }
}