package com.caodaxing.main.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caodaxing.main.entity.SysFile;
import com.caodaxing.main.service.SysFileService;
import com.caodaxing.main.util.ExcelUtils;
import com.caodaxing.main.util.HTTPDataUtils;

@Controller
@RequestMapping("fileinfo")
public class FileInfoController {
	
	@Autowired
	private SysFileService sysFileService;
	
	@RequestMapping("findFileInfo.do")
	@ResponseBody
	public Map<String, Object> findFileInfo(int currentPage, int pageSize) {
		currentPage = currentPage/pageSize + 1;
		System.out.println(currentPage);
		return sysFileService.findAllFile(currentPage, pageSize);
	}
	
	@RequestMapping("excelExport")
	public void excelExport(HttpServletRequest request, HttpServletResponse resp) {
		Map<String, Object> map = new HashMap<>();
		map.put("fileid", "文件序号");
		map.put("filename", "文件名");
		map.put("filetype", "文件类型");
		map.put("createdate", "创建时间");
		map.put("filepath", "文件路径");
		List<SysFile> fileList = sysFileService.selectSysFileList(null, null, 10);
		try {
			resp.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
			resp.setHeader("Content-Disposition", "attachment; filename=" + new String("很好.xlsx".getBytes("iso-8859-1")));
			ServletOutputStream out = resp.getOutputStream();
			ExcelUtils.exportExcelX(out, map, fileList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("httpData")
	@ResponseBody
	public String urlReadDate() {
		String urlString = "http://localhost:8088/FileManage/index/imageList.do";
		return HTTPDataUtils.takeDataToUrl(urlString,null);
	}
}
