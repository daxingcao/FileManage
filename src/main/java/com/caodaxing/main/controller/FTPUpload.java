package com.caodaxing.main.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.caodaxing.main.util.FileTypeUtil;

@Controller
@RequestMapping("caodaxing")
public class FTPUpload {

	public static long size = 0;
	public static long num = 0;
	public static boolean flag = false;
	
	@RequestMapping(value = "uploadFTP1.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadUtils(@RequestParam MultipartFile[] myfiles){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			for (MultipartFile myfile : myfiles) {
				InputStream is = myfile.getInputStream();
				String fileName = myfile.getOriginalFilename();
				boolean result = this.uploadFTP("/2018-01-26", fileName, is);
				if(result) {
					map.put("result", result);
					map.put("msg", "上传成功");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public boolean uploadFTP(String filePath,String fileName,InputStream input) {
		FTPClient ftp = new FTPClient();
		try {
			ftp.connect("172.16.12.20", 2121);
			ftp.login("ftpuser", "123");
			int reply = ftp.getReplyCode();
			if(!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return false;
			}
			if(!ftp.changeWorkingDirectory(filePath)) {
				if(ftp.makeDirectory(filePath)) {
					ftp.changeWorkingDirectory(filePath);
				}else {
					return false;
				}
			}
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			System.out.println(ftp.getCharsetName());
			System.out.println(ftp.getCharsetName());
			if(!ftp.storeFile(fileName, input)) {
				System.out.println("上传失败！");
				return false;
			}
			input.close();
			ftp.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	@RequestMapping("downloadFTP")
	public void downloadFTP(String basicPath,String fileName) {
		FTPClient ftp = new FTPClient();
		try {
			fileName = new String(fileName.getBytes("iso-8859-1"));
			ftp.connect("172.16.12.20", 2121);
			ftp.login("ftpuser", "123");
			if(!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.disconnect();
			}
			ftp.changeWorkingDirectory(basicPath);
			ftp.setControlEncoding("gbk");
			ftp.enterLocalPassiveMode();
			FTPFile[] listFiles = ftp.listFiles();
			for (FTPFile ftpFile : listFiles) {
				if(ftpFile.getName().equals(fileName)) {
					System.out.println("找到文件了");
					num = ftpFile.getSize();
					File file = new File("d:/java/"+fileName);
					OutputStream os = new FileOutputStream(file);
					InputStream is = ftp.retrieveFileStream(ftpFile.getName());
					int len = 0;
					byte[] buffer = new byte[10];
					testSize test = new testSize();
					test.start();
					while((len = is.read(buffer)) > 0) {
						size = size + len;
						os.write(buffer, 0, len);
						os.flush();
					}
					flag = true;
					Thread.sleep(1000);
					
					is.close();
					os.close();
				}
			}
			ftp.logout();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 浏览器下载
	 * @param basicPath
	 * @param fileName
	 */
	@RequestMapping("downloadFTP2")
	public ResponseEntity<byte[]> downloadFTP(String basicPath,String fileName, HttpServletResponse resp) {
		FTPClient ftp = new FTPClient();
		try {
			ftp.connect("172.16.12.20", 2121);
			ftp.login("ftpuser", "123");
			if(!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.disconnect();
			}
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			ftp.setControlEncoding("gbk");
			ftp.changeWorkingDirectory(basicPath);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			header.setContentDispositionFormData("attachment", fileName);
			fileName = new String(fileName.getBytes("iso-8859-1"));
			InputStream is = ftp.retrieveFileStream(new String(fileName.getBytes("GBK"),"ISO-8859-1"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int len = 0;
			byte[] b = new byte[10];
			while((len = is.read(b)) != -1) {
				baos.write(b, 0, len);
				baos.flush();
			}
//			header.setContentLength(baos.size());
			is.close();
			ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(baos.toByteArray(), header, HttpStatus.CREATED);
			return responseEntity;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	@RequestMapping("showFile")
	@ResponseBody
	public List<Map<String, Object>> FTPFile(String filePath){
		FTPClient ftp = new FTPClient();
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			ftp.connect("172.16.12.20", 2121);
			ftp.login("ftpuser", "123");
			int reply = ftp.getReplyCode();
			if(!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return null;
			}
			if(filePath == null) {
				filePath = "/";
			}
			ftp.setControlEncoding("gbk");
			FTPFile[] listFiles = ftp.listFiles(filePath);
			Map<String, Object> map = null;
			for (FTPFile ftpFile : listFiles) {
				String fileName = ftpFile.getName();
				map = new HashMap<String, Object>();
				if(ftpFile.isFile()) {
					map.put("kind", 0);
					map.put("type", FileTypeUtil.fileType(ftpFile.getName()));
				}else if (ftpFile.isDirectory()) {
					map.put("kind", 1);
				}
				map.put("basicPath", filePath);
				map.put("fileName", fileName);
				if(ftpFile.isDirectory()) {
					list.add(0, map);
				}else {
					list.add(map);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}

class testSize extends Thread {
	
	public long lastSize = 0L;
	
	@Override
	public void run() {
		try {
			while(!FTPUpload.flag) {
				System.out.println("传输大小："+FTPUpload.size);
				System.out.println("总量："+FTPUpload.num);
				double s = FTPUpload.size/(double)FTPUpload.num;
				DecimalFormat nf = new DecimalFormat("###");
				String formatNumber = nf.format(s*100);
				System.out.println("下载进度："+formatNumber+"%");
				int timeout = 0;
				Thread.sleep(1000);
				if(FTPUpload.size > lastSize) {
					lastSize = FTPUpload.size;
					timeout = 0;
				}else {
					timeout += 1000;
				}
				if(timeout >= 60000) {
					throw new Exception("");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
