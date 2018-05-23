package com.caodaxing.main.util;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.caodaxing.main.entity.ReturnS;

public class FTPUtils {

	private static String host;
	private static int port;
	private static String userName;
	private static String pwd;
	
	static {
		Properties pros = FileLoadUtil.loadProperties("ftp.properties");
		if(pros != null) {
			host = pros.getProperty("ftp.host");
			port = Integer.parseInt(pros.getProperty("ftp.port"));
			userName = pros.getProperty("ftp.username");
			pwd = pros.getProperty("ftp.password");
		}
	}
	
	public static Map<String, Object> updateFileToFTP(InputStream is ,String fileName ,String basicPath) throws Exception {
		FTPClient ftp = new FTPClient();
		ftp.connect(host, port);
		ftp.login(userName, pwd);
		int code = ftp.getReplyCode();
		if(!FTPReply.isPositiveCompletion(code)) {
			ftp.disconnect();
			return MessageUtils.errorMessage("ftp连接失败。。。");
		}
		StringBuffer str = new StringBuffer();
		if(!ftp.changeWorkingDirectory(basicPath)) {
			String[] dirs = basicPath.split("/");
			for (String dir : dirs) {
				if(dir == null || "".equals(dir))
					continue;
				str.append("/" +dir);
				if(!ftp.changeWorkingDirectory(str.toString())) {
					if(ftp.makeDirectory(str.toString())) {
						ftp.changeWorkingDirectory(str.toString());
						basicPath = str.toString();
					}else {
						return MessageUtils.errorMessage("新建文件夹失败。。。");
					}
				}
			}
		}
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		if(ftp.storeFile(fileName, is)) {
			Map<String, Object> map = MessageUtils.successMessage("上传成功！");
			map.put("realPath", basicPath);
			ftp.logout();
			ftp.disconnect();
			is.close();
			return map;
		}
		return MessageUtils.errorMessage("上传失败！");
		
	}
	
	public static InputStream downloadFile(String filePath, String fileName) throws Exception {
		FTPClient ftp = new FTPClient();
		ftp.connect(host, port);
		ftp.login(userName, pwd);
		int code = ftp.getReplyCode();
		if(!FTPReply.isPositiveCompletion(code)) {
			ftp.disconnect();
			return null;
		}
		if(!ftp.changeWorkingDirectory(filePath)) {
			return null;
		}
		return ftp.retrieveFileStream(fileName);
		
	}
	
}
