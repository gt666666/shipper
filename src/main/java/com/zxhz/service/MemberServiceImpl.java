package com.zxhz.service;

import com.zxhz.dao.IActionDAO;
import com.zxhz.dao.IMemberDAO;
import com.zxhz.dao.IRoleDAO;
import com.zxhz.dao.IUserInfoDAO;
import com.zxhz.pojo.Member;
import com.zxhz.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 类描述：
 * 创建作者：gt
 * 创建日期 ： 2019/11/19
 */
@Service
public class MemberServiceImpl {
    @Resource
    private IActionDAO actionDAO;
    @Resource
    private IMemberDAO memberDAO;
    @Resource
    private IRoleDAO roleDAO;
    @Resource
    private IUserInfoDAO userInfoDAO;


    public Member findById(String mid) {
        return this.memberDAO.findById(mid);
    }

    public int insertUserInfo(UserInfo userInfo) {

        return this.userInfoDAO.insert(userInfo);
    }

    public List<UserInfo> findByQuery(UserInfo userInfo) {
        return this.userInfoDAO.findByQuery(userInfo);
    }


    public int updateById(UserInfo userInfo) {
            return this.userInfoDAO.updateById(userInfo);
    }

    public Map<String, Set<String>> listAuthByMember(String mid) {
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();
        map.put("allRoles", this.roleDAO.findAllRoleByMember(mid));
        map.put("allActions", this.actionDAO.findAllActionByMember(mid));
        return map;
    }
}
