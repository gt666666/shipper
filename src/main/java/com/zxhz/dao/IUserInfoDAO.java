package com.zxhz.dao;

import com.zxhz.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 类描述：
 * 创建作者：gt
 * 创建日期 ： 2019/12/11
 */
@Mapper
public interface IUserInfoDAO {
     int insert(UserInfo userInfo);
     int updateById(UserInfo userInfo);
     List<UserInfo>  findByQuery(UserInfo userInfo);
}
