package com.caodaxing.main.util;

import java.util.HashMap;
import java.util.Map;

public class FileTypeUtil {
	
	public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();     
    
    private FileTypeUtil(){}
    
    static{     
        getAllFileType(); //初始化文件类型信息     
    }
    
    private static void getAllFileType() {     
        FILE_TYPE_MAP.put("jpg","tupian"); //JPEG (jpg)     
        FILE_TYPE_MAP.put("png","tupian"); //PNG (png)     
        FILE_TYPE_MAP.put("gif","tupian"); //GIF (gif)     
        FILE_TYPE_MAP.put("tif","unknow"); //TIFF (tif)     
        FILE_TYPE_MAP.put("bmp","unknow"); //16色位图(bmp)
        FILE_TYPE_MAP.put("cfg","unknow"); //16色位图(bmp)
//        FILE_TYPE_MAP.put("unknow", "bmp"); //24位位图(bmp)     
//        FILE_TYPE_MAP.put("unknow", "bmp"); //256色位图(bmp)     
        FILE_TYPE_MAP.put("dwg","unknow"); //CAD (dwg)     
        FILE_TYPE_MAP.put("html","unknow"); //HTML (html)
        FILE_TYPE_MAP.put("htm","unknow"); //HTM (htm)
        FILE_TYPE_MAP.put("css","unknow"); //css
        FILE_TYPE_MAP.put("js","unknow"); //js
        FILE_TYPE_MAP.put("txt","wenjian"); //js
        FILE_TYPE_MAP.put("rtf","unknow"); //Rich Text Format (rtf)     
        FILE_TYPE_MAP.put("psd","unknow"); //Photoshop (psd)     
        FILE_TYPE_MAP.put("eml","unknow"); //Email [Outlook Express 6] (eml)       
        FILE_TYPE_MAP.put("doc","wenjian"); //MS Excel 注意：word、msi 和 excel的文件头一样     
        FILE_TYPE_MAP.put("vsd","wenjian"); //Visio 绘图     
        FILE_TYPE_MAP.put("mdb","wenjian"); //MS Access (mdb)      
        FILE_TYPE_MAP.put("ps","wenjian");     
        FILE_TYPE_MAP.put("pdf","wenjian"); //Adobe Acrobat (pdf)   
        FILE_TYPE_MAP.put("rmvb","shipin"); //rmvb/rm相同  
        FILE_TYPE_MAP.put("flv","shipin"); //flv与f4v相同  
        FILE_TYPE_MAP.put("mp4","shipin"); 
        FILE_TYPE_MAP.put("mp3","yinyue"); 
        FILE_TYPE_MAP.put("mpg","yinyue"); //     
        FILE_TYPE_MAP.put("wmv","shipin"); //wmv与asf相同    
        FILE_TYPE_MAP.put("wav","shipin"); //Wave (wav)  
        FILE_TYPE_MAP.put("avi","shipin");  
        FILE_TYPE_MAP.put("mid","unknow"); //MIDI (mid)   
        FILE_TYPE_MAP.put("zip","yasuo");    
        FILE_TYPE_MAP.put("rar","yasuo");   
        FILE_TYPE_MAP.put("ini","unknow");   
        FILE_TYPE_MAP.put("jar","yasuo"); 
        FILE_TYPE_MAP.put("exe","unknow");//可执行文件
        FILE_TYPE_MAP.put("jsp","unknow");//jsp文件
        FILE_TYPE_MAP.put("mf","unknow");//MF文件
        FILE_TYPE_MAP.put("xml","wenjian");//xml文件
        FILE_TYPE_MAP.put("sql","wenjian");//xml文件
        FILE_TYPE_MAP.put("java","wenjian");//java文件
        FILE_TYPE_MAP.put("bat","yasuo");//bat文件
        FILE_TYPE_MAP.put("gz","yasuo");//gz文件
        FILE_TYPE_MAP.put("properties","wenjian");//bat文件
        FILE_TYPE_MAP.put("class","wenjian");//bat文件
        FILE_TYPE_MAP.put("chm","unknow");//bat文件
        FILE_TYPE_MAP.put("mxp","unknow");//bat文件
        FILE_TYPE_MAP.put("docx","wenjian");//docx文件
        FILE_TYPE_MAP.put("wps","wenjian");//WPS文字wps、表格et、演示dps都是一样的
        FILE_TYPE_MAP.put("torrent","unknow");
        FILE_TYPE_MAP.put("mov","unknow"); //Quicktime (mov)  
        FILE_TYPE_MAP.put("wpd","unknow"); //WordPerfect (wpd)   
        FILE_TYPE_MAP.put("dbx","unknow"); //Outlook Express (dbx)     
        FILE_TYPE_MAP.put("pst","unknow"); //Outlook (pst)      
        FILE_TYPE_MAP.put("qdf","unknow"); //Quicken (qdf)     
        FILE_TYPE_MAP.put("pwl","unknow"); //Windows Password (pwl)         
        FILE_TYPE_MAP.put("ram","unknow"); //Real Audio (ram)     
    }
    
    public static String fileType(String fileName) {
    	String[] str = fileName.split("\\.");
    	if(str != null) {
    		return FILE_TYPE_MAP.get(str[(str.length-1)]);
    	}
    	return "unknow";
    }
    
}
