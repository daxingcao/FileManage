package com.caodaxing.main.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.caodaxing.main.dao.UserInfoDao;
import com.caodaxing.main.entity.LoginUser;
import com.caodaxing.main.entity.UserInfo;
import com.caodaxing.main.service.LoginUserService;

@Component
public class JobQuartzTest {
	
	@Autowired
	private LoginUserService loginUserService;
	@Autowired
	private UserInfoDao userInfoDao;
	
	public void execute() {
		LoginUser queryLoginUser = loginUserService.queryLoginUser(2l);
		System.out.println(queryLoginUser.toString()+"------"+ new Date());
	}
	
	public void executeTwo() {
		UserInfo user = new UserInfo();
		user.setUserAddress("shanghai");
		user.setUserAge(20);
		user.setUserName("hery");
		user.setUserGander(1);
		int result = userInfoDao.insertSelective(user);
		System.out.println(result+"------"+ new Date());
	}

}
