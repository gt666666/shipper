package com.zxhz.controller;

import com.github.pagehelper.PageHelper;
import com.zxhz.controller.abs.AbstractBaseController;
import com.zxhz.enums.ResultEnum;
import com.zxhz.pojo.CommonResult;
import com.zxhz.pojo.Member;
import com.zxhz.pojo.UserInfo;
import com.zxhz.service.MemberServiceImpl;
import com.zxhz.utils.Page;
import com.zxhz.utils.PaginationContext;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 类描述：
 * 创建作者：gt
 * 创建日期 ： 2019/11/20
 */
@RestController
@Slf4j
public class MemberController extends AbstractBaseController {
    @Resource
    private MemberServiceImpl memberService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
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
        return member;
    }

    @PostMapping("/insertUserInfo")
    public CommonResult insertUserInfo(UserInfo userInfo, MultipartFile photo) {
       String pathName = "/usr/data/www/images/";//想要存储文件的地址
        //String  pathName="F:\\image\\";
        String pname = "";
        if (photo != null) {
            pname = photo.getOriginalFilename();//获取文件名（包括后缀）
            pathName += pname;
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(pathName);
                fos.write(photo.getBytes()); // 写入文件
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String str = "";
        if (userInfo.getHobbys().length > 0) {
            for (int i = 0; i < userInfo.getHobbys().length; i++) {
                str += userInfo.getHobbys()[i] + ",";
            }
            str = str.substring(0, str.lastIndexOf(","));
        }
        userInfo.setCreateDate(new Date());
        userInfo.setHobby(str);
        userInfo.setImg("images/"+pathName);
        int i = this.memberService.insertUserInfo(userInfo);
        if (i == 1) {
            return resultWrapper(ResultEnum.INSERT_SUCCESS);
        }
        return resultWrapper(ResultEnum.INSERT_ERROR);
    }

    @GetMapping("/findByQuery")
    public Page<UserInfo> findByQuery(UserInfo userInfo) {
        PageHelper.startPage(PaginationContext.getPageNum(), PaginationContext.getPageSize());
        return new Page<UserInfo>(this.memberService.findByQuery(userInfo));
    }

    @PostMapping("/updateById")
    public CommonResult updateById(@RequestBody UserInfo userInfo) {
        String str = "";
        if (userInfo.getHobbys().length > 0) {
            for (int i = 0; i < userInfo.getHobbys().length; i++) {
                str += userInfo.getHobbys()[i] + ",";
            }
            str = str.substring(0, str.lastIndexOf(","));
        }
        userInfo.setHobby(str);
        int i = this.memberService.updateById(userInfo);
        if (i == 1) {
            return resultWrapper(ResultEnum.UPDATE_SUCCESS);
        }
        return resultWrapper(ResultEnum.UPDATE_ERROR);
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
    @PostMapping(value = "/login")
    public CommonResult login(String mid, String password) {
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

        password = new Md5Hash(password, mid, 3).toString();
        UsernamePasswordToken upToken = new UsernamePasswordToken(mid, password);
        //1.获取subject
        Subject subject = SecurityUtils.getSubject();
        String session = (String) subject.getSession().getId(); //获取session
        Member member = this.memberService.findById(mid);   //获取登录用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("session", session);  //登录的session
        map.put("member", member);  //登录用户信息
        //2.调用subject进行登录
        subject.login(upToken);
        Map<String, Set<String>> mRolesActions = this.memberService.listAuthByMember(mid);//获取用户的角色和权限
        map.put("mRolesActions", mRolesActions);  //登录的用户角色和权限
        this.redisTemplate.opsForHash().putAll(session,map);
        this.redisTemplate.expire(session,8,TimeUnit.HOURS);   //登录信息8小时后过期
        return resultDataWrapper(ResultEnum.LOGIN_SUCCESS, map); //返回登录成功信息、session,用户名信息
    }

    //前台传来的token获取相应的角色信息
    @PostMapping(value = "/getRoles")
    public Object getRoles(String token) {
        return this.redisTemplate.opsForHash().entries(token);
    }

    @GetMapping("/autherror")
    public String autherror(int code) {
        return code == 1 ? "未登录" : "未授权";
    }
}
