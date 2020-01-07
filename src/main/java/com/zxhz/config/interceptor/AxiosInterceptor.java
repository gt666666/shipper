package com.zxhz.config.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 类描述： 拦截器  是否携带token
 * 创建作者：gt
 * 创建日期 ： 2019/12/10
 */
@Component
public class AxiosInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String token=request.getHeader("token");  //随头信息发送token
        System.err.println("【AxiosInterceptor.preHandle()】接收客户端发送的Token头信息:"+token);
        if(token==null||"undefined".equals(token)){   //没有指定的token
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //设置状态码  401
            try {
                response.getWriter().write(String.format("{'code':%s,'message':'%s'}", HttpServletResponse.SC_UNAUTHORIZED, "Invlidate Request Token"));
                return  false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
