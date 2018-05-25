package com.caodaxing.main.service;

import java.util.Map;

import com.caodaxing.main.entity.LoginUser;
import com.caodaxing.main.entity.UserInfo;

public interface LoginUserService {

	LoginUser queryLoginUser(Long id);
	
	UserInfo queryUserInfo(int id);
	
	Map<String, Object> selectLastDate(Long id);
	
}
