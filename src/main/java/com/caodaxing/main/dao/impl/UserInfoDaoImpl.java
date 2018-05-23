package com.caodaxing.main.dao.impl;

import org.springframework.stereotype.Service;

import com.caodaxing.main.basic.Common;
import com.caodaxing.main.dao.UserInfoDao;
import com.caodaxing.main.entity.UserInfo;

@Service
public class UserInfoDaoImpl extends Common implements UserInfoDao {

	private final String MAPPER = "com.caodaxing.main.dao.UserInfoMapper.";
	
	@Override
	public int deleteByPrimaryKey(Integer infoId) {
		return this.getSqlSession().delete(MAPPER+"deleteByPrimaryKey", infoId);
	}

	@Override
	public int insert(UserInfo record) {
		return this.getSqlSession().insert(MAPPER+"insert", record);
	}

	@Override
	public int insertSelective(UserInfo record) {
		return this.getSqlSession().insert(MAPPER+"insertSelective", record);
	}

	@Override
	public UserInfo selectByPrimaryKey(Integer infoId) {
		return this.getSqlSession().selectOne(MAPPER+"selectByPrimaryKey", infoId);
	}

	@Override
	public int updateByPrimaryKeySelective(UserInfo record) {
		return this.getSqlSession().update(MAPPER+"updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(UserInfo record) {
		return this.getSqlSession().update(MAPPER+"updateByPrimaryKey", record);
	}

}
