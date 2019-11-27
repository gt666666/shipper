package com.zxhz.config.shiro;

import com.zxhz.pojo.Member;
import com.zxhz.service.MemberServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 自定义的realm
 */
public class CustomRealm extends AuthorizingRealm {

    public void setName(String name) {
        super.setName("customRealm");
    }
    @Autowired
    private MemberServiceImpl memberService;
    /**
     * 认证方法
     * 参数：传递的用户名密码
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取登录的用户名密码（token）
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String mid = upToken.getUsername();
        String password = new String(upToken.getPassword());
        //2.根据用户名查询数据库
        Member member = memberService.findById(mid);
        //3.表示该用户信息不存在，不存在则应该抛出一个异常
        if (member == null) {
            throw new UnknownAccountException("用户名不存在！");
        }
        //4.判断密码是否正确
        if (!password.equals(member.getPassword())) { // 密码验证
            throw new IncorrectCredentialsException("密码错误！");
        }
        // 5.随后还需要考虑用户被锁定的问题
        if (member.getLocked().equals(1)) { // 1表示锁定，0表示没有锁定
            throw new LockedAccountException("被锁了，找管理员解锁去吧！");
        }
        //6.如果一致返回安全数据
        //构造方法：安全数据，密码，realm域名
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(member, member.getPassword(), this.getName());
        return info;
    }

    /**
     * 授权方法
     * 操作的时候，判断用户是否具有相应的权限
     * 先认证 -- 安全数据
     * 再授权 -- 根据安全数据获取用户具有的所有操作权限
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.获取已认证的用户数据
        Member member = (Member) principalCollection.getPrimaryPrincipal();//得到唯一的安全数据
        //2.根据用户数据获取用户的权限信息（所有角色，所有权限）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Map<String, Set<String>> map = this.memberService.listAuthByMember(member.getMid());
        Set<String> allRoles = new HashSet<>();//所有角色
        Set<String> allActions = new HashSet<>();//所有权限
        allRoles.addAll(map.get("allRoles"));
		allActions.addAll(map.get("allActions")) ;
        info.setStringPermissions(allActions);
        info.setRoles(allRoles);
        return info;
    }





    public static void main(String[] args) {
        System.out.println(new Md5Hash("123456", "java", 3).toString());
    }
}