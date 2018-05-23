package com.caodaxing.main.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.caodaxing.main.basic.Common;
import com.caodaxing.main.dao.SysFileDao;
import com.caodaxing.main.entity.SysFile;
@Service
public class SysFileDaoImpl extends Common implements SysFileDao {

	private final String MAPPER = "com.caodaxing.main.dao.SysFileMapper.";
	
	@Override
	public int deleteByPrimaryKey(Integer fileid) {
		return this.getSqlSession().delete(MAPPER+"deleteByPrimaryKey", fileid);
	}

	@Override
	public int insert(SysFile record) {
		return this.getSqlSession().insert(MAPPER+"insert", record);
	}

	@Override
	public int insertSelective(SysFile record) {
		return this.getSqlSession().insert(MAPPER+"insertSelective", record);
	}

	@Override
	public SysFile selectByPrimaryKey(Integer fileid) {
		return this.getSqlSession().selectOne(MAPPER+"selectByPrimaryKey", fileid);
	}

	@Override
	public int updateByPrimaryKeySelective(SysFile record) {
		return this.getSqlSession().update(MAPPER+"updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(SysFile record) {
		return this.getSqlSession().update(MAPPER+"updateByPrimaryKey", record);
	}

	@Override
	public List<SysFile> selectSysFileList(String filePath, String fileType, int showNum) {
		Map<String, Object> map = new HashMap<>();
		map.put("filePath", filePath);
		map.put("fileType", fileType);
		map.put("showNum", showNum);
		return this.getSqlSession().selectList(MAPPER+"selectSysFileList",map);
	}

	@Override
	public List<SysFile> findAllFile() {
		return this.getSqlSession().selectList(MAPPER+"findAllFile");
	}

}
