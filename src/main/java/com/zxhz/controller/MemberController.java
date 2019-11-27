package com.zxhz.controller;

import com.zxhz.pojo.Member;
import com.zxhz.service.MemberServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * 类描述：
 * 创建作者：gt
 * 创建日期 ： 2019/11/20
 */
@RestController

public class MemberController {
    @Resource
    private MemberServiceImpl memberService;
    //使用shiro注解鉴权
    //@RequiresPermissions()  -- 访问此方法必须具备的权限
    //@RequiresRoles() -- 访问此方法必须具备的角色
    /**
     * 1.过滤器：如果权限信息不匹配跳转setUnauthorizedUrl地址
     * 2.注解：如果权限信息不匹配，抛出异常
     */
    @RequiresPermissions("dept:list")
    @GetMapping("/member/home")
    public String home() {
        return "访问主页";
    }
    @GetMapping("/member/findById")
    public Member findById(String mid) {
        return this.memberService.findById(mid);
    }

    @GetMapping("/member/listAuthByMember")
    public Map<String, Set<String>> listAuthByMember(String mid) {
        return this.memberService.listAuthByMember(mid);
    }
    @GetMapping("/member/madd")
    public Member madd(Member member) {
        ;
        return member;
    }

    @GetMapping("/member/add")
    public String add() {
        return "增加成功";
    }

    /**
     * shiro登录
     * 前端发送登录请求 => 接口部分获取用户名密码 => 通过subject.login =>  realm域的认证方法
     */
    //用户登录
    @RequestMapping(value = "/login")
    public String login(String username, String password) {
        //构造登录令牌
            /**
             * 密码加密：
             *     shiro提供的md5加密
             *     Md5Hash:
             *      参数一：加密的内容
             *              111111   --- abcd
             *      参数二：盐（加密的混淆字符串）（用户登录的用户名）
             *              111111+混淆字符串
             *      参数三：加密次数
             *
             */
            password = new Md5Hash(password, username, 3).toString();

            UsernamePasswordToken upToken = new UsernamePasswordToken(username, password);
            //1.获取subject
            Subject subject = SecurityUtils.getSubject();

            //获取session
            String sid = (String) subject.getSession().getId();
            System.out.println(sid);
            //2.调用subject进行登录
            subject.login(upToken);
            return "登录成功";
    }
    @GetMapping("/autherror")
    public String autherror(int code) {
        return code == 1 ? "未登录" : "未授权";
    }
}
