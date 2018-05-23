package com.caodaxing.main.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caodaxing.main.dao.LoginUserDao;
import com.caodaxing.main.dao.UserInfoDao;
import com.caodaxing.main.entity.LoginUser;
import com.caodaxing.main.entity.UserInfo;
import com.caodaxing.main.service.LoginUserService;

@Service
public class LoginUserServiceImpl implements LoginUserService {

	@Autowired
	private LoginUserDao loginUserDao;
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Override
	public LoginUser queryLoginUser(Long id) {
		return loginUserDao.selectByPrimaryKey(id);
	}

	@Override
	public UserInfo queryUserInfo(int id) {
		return userInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> selectLastDate(Long id) {
		return loginUserDao.selectLastDate(id);
	}

}
