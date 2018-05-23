package com.caodaxing.main.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class HTTPDataUtils {
	
	/**
	 * 读取远程接口数据
	 * @param urlString 远程URL连接地址
	 * @return 数据字符串
	 */
	public static String takeDataToUrl(String urlString, String jsonParams) {
		StringBuffer result = null;
		BufferedReader br = null;
		try {
			URI uri = new URI(urlString);
			URL url = uri.toURL();
			URLConnection connect = url.openConnection();
			connect.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connect.setRequestProperty("Charset", "utf-8");
			connect.connect();
			if(jsonParams != null && !jsonParams.isEmpty()) {
				connect.setDoOutput(true);
				DataOutputStream dos = new DataOutputStream(connect.getOutputStream());
				dos.writeBytes(jsonParams);
				dos.flush();
				dos.close();
			}
			result = new StringBuffer();
			br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
			String line;
			while((line = br.readLine()) != null) {
				result.append(line);
			}
			List<Map> parseArray = JSONObject.parseArray(result.toString(), Map.class);
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
		return null;
	}

}
