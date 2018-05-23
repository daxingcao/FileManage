package com.caodaxing.main.dao;

import java.util.Date;
import java.util.Map;

import com.caodaxing.main.entity.LoginUser;

public interface LoginUserDao {

    int deleteByPrimaryKey(Long id);

    int insert(LoginUser record);

    int insertSelective(LoginUser record);

    LoginUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LoginUser record);

    int updateByPrimaryKey(LoginUser record);
    
    Map<String, Object> selectLastDate(Long id);
}