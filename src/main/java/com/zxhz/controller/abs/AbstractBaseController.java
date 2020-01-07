package com.zxhz.controller.abs;

import com.zxhz.enums.ResultEnum;
import com.zxhz.pojo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import java.util.Locale;

/**
 * 类描述：
 * 创建作者：gt
 * 创建日期 ： 2019/11/25
 */
public abstract class AbstractBaseController {
    @Autowired
    private MessageSource messageSource;

    public String getMessage(String key, String... args) {
        return this.messageSource.getMessage(key, args, Locale.getDefault());
    }
    public CommonResult  resultWrapper(ResultEnum resultEnum ){
        CommonResult  commonResult=new CommonResult();
        commonResult.setCode(resultEnum.getCode());
        commonResult.setMsg(resultEnum.getMessage());
        return   commonResult;
    }
    public CommonResult  resultDataWrapper(ResultEnum resultEnum,Object obj ){
        CommonResult  commonResult=new CommonResult();
        commonResult.setCode(resultEnum.getCode());
        commonResult.setMsg(resultEnum.getMessage());
        commonResult.setData(obj);
        return   commonResult;
    }
}
