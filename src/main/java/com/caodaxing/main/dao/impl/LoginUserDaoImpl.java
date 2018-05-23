package com.caodaxing.main.dao.impl;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.caodaxing.main.basic.Common;
import com.caodaxing.main.dao.LoginUserDao;
import com.caodaxing.main.entity.LoginUser;

@Service
public class LoginUserDaoImpl extends Common implements LoginUserDao {

	private final String MAPPER = "com.caodaxing.main.dao.LoginUserMapper.";
	
	@Override
	public int deleteByPrimaryKey(@Param("id")Long id) {
		return this.getSqlSession().delete(MAPPER+"deleteByPrimaryKey", id);
	}

	@Override
	public int insert(LoginUser record) {
		return this.getSqlSession().insert(MAPPER+"insert",record);
	}

	@Override
	public int insertSelective(LoginUser record) {
		return this.getSqlSession().insert(MAPPER+"insertSelective",record);
	}

	@Override
	public LoginUser selectByPrimaryKey(Long id) {
		return this.getSqlSession().selectOne(MAPPER+"selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(LoginUser record) {
		return this.getSqlSession().update(MAPPER+"updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(LoginUser record) {
		return this.getSqlSession().update(MAPPER+"updateByPrimaryKey", record);
	}

	@Override
	public Map<String, Object> selectLastDate(Long id) {
		return this.getSqlSession().selectOne(MAPPER+"selectLastDate", id);
	}

}
