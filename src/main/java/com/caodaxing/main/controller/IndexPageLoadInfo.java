package com.caodaxing.main.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caodaxing.main.entity.SysFile;
import com.caodaxing.main.service.SysFileService;
import com.caodaxing.main.util.FileLoadUtil;

@Controller
@RequestMapping("index")
public class IndexPageLoadInfo {
	
	private static Properties pros;
	
	static {
		pros = FileLoadUtil.loadProperties("index.properties");
	}

	@Autowired
	private SysFileService sysFileService;
	
	@RequestMapping("imageList.do")
	@ResponseBody
	public List<SysFile> imageList(HttpServletRequest req) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
//		StringBuffer str = new StringBuffer();
//		String s = null;
//		while((s = br.readLine())!=null) {
//			str.append(s);
//		}
//		System.out.println(str.toString());
		String filePath = pros.getProperty("image.filePath");
		String fileType = pros.getProperty("image.fileType");
		String showNum = pros.getProperty("image.showNum");
		List<SysFile> sysFileList = sysFileService.selectSysFileList(filePath, fileType, Integer.parseInt(showNum));
		return sysFileList;
	}
	
}
