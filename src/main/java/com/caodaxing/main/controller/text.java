package com.caodaxing.main.controller;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.caodaxing.main.entity.SysFile;
import com.caodaxing.main.service.SysFileService;
import com.caodaxing.main.util.DateUtil;
import com.caodaxing.main.util.FTPUtils;

@Controller
public class text {

	@Autowired
	private SysFileService sysFileService;
	
	@RequestMapping(value = "updateFile.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateFile(HttpServletRequest req,HttpServletResponse resp,
			@RequestParam("myFile") MultipartFile myFile ,String basicPath) {
		Map<String, Object> map = null;
		String contentType = myFile.getContentType();
		String name = myFile.getOriginalFilename();
		String fileName = DateUtil.formatDate(new Date(), "yyyyMMddHHmmss")+name.substring(name.lastIndexOf("."), name.length());
		try {
			map = FTPUtils.updateFileToFTP(myFile.getInputStream(),fileName,basicPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if("0".equals(map.get("returnCode"))) {
			SysFile sysFile = new SysFile();
			if(map.get("realPath") != null) {
				sysFile.setFilepath(map.get("realPath").toString());
			}
			sysFile.setFilename(fileName);
			sysFile.setFiletype(contentType);
			sysFile.setCreatedate(new Date());
			sysFileService.insert(sysFile);
		}
		return map;
	}
	
	@RequestMapping("loadImage.do")
	public void loadImage(HttpServletRequest req,HttpServletResponse resp) {
		String fileId = req.getParameter("fileId");
		SysFile sysFile = sysFileService.selectByPrimaryKey(Integer.valueOf(fileId));
		resp.setContentType("image/*");
		try {
			InputStream is = FTPUtils.downloadFile(sysFile.getFilepath(), sysFile.getFilename());
			BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
			int len = 0;
			byte[] b = new byte[2014];
			while((len = is.read(b)) > 0) {
				bos.write(b, 0, len);
			}
			bos.flush();
			is.close();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
