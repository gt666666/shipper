package com.zxhz.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 类描述：
 * 创建作者：gt
 * 创建日期 ： 2019/11/19
 */
@Mapper
public interface IRoleDAO {
    public Set<String> findAllRoleByMember(String  mid);
}
