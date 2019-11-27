package com.zxhz.config.shiro;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义的sessionManager
 */
public class CustomSessionManager extends DefaultWebSessionManager {
    /**
     * 头信息中具有sessionid
     * 请求头：Authorization： sessionid
     * <p>
     * 指定sessionId的获取方式
     */
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

        //获取请求头Authorization中的数据
        String Authorization = request.getParameter("Authorization");
        //String id = WebUtils.toHttp(request).getHeader("Authorizations");
        if (StringUtils.isEmpty(Authorization)) {
            //如果没有携带，生成新的sessionId
            return super.getSessionId(request, response);
        } else {
            //返回sessionId；
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "header");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, Authorization);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return Authorization;
        }
    }
}