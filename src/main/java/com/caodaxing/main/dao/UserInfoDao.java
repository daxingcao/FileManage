package com.caodaxing.main.dao;

import com.caodaxing.main.entity.UserInfo;

public interface UserInfoDao {


    int deleteByPrimaryKey(Integer infoId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer infoId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}