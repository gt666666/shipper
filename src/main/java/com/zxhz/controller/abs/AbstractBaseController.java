package com.zxhz.controller.abs;

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
}
