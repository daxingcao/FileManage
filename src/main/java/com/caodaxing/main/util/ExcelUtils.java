package com.caodaxing.main.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;



/**
 * poi解析Excel工具
 * @author oracle
 */
public class ExcelUtils
{
	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
	public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
//	public static String NO_DEFINE = "no_define";// 未定义的字段
//	public static String DEFAULT_DATE_PATTERN = "yyyy年MM月dd日";// 默认日期格式
//	public static int DEFAULT_COLOUMN_WIDTH = 17;
	public static Logger log = Logger.getLogger(ExcelUtils.class);
	
	public static Gson gson = new Gson();

	/**
	 * 获取Workbook
	 * @param fileName 文件名
	 * @return Workbook
	 * @throws Exception 异常
	 */
	public static Workbook getWorkbook(InputStream is,String fileName) throws Exception
	{
		Workbook wb = null;
		// 建立输入流
//		InputStream input = new FileInputStream(fileName);
		// 获取文件后缀名
		String filePostFixName = getPostfix(fileName);
		// 根据文件格式(2003或者2007)来初始化
		if (filePostFixName.equals(OFFICE_EXCEL_2010_POSTFIX))
		{
			wb = new XSSFWorkbook(is);
		}
		else if (filePostFixName.equals(OFFICE_EXCEL_2003_POSTFIX))
		{
			wb = new HSSFWorkbook(is);
		}
		return wb;
	}

	@SuppressWarnings("deprecation")
	public static String getCellValue(Cell cell)
	{
		String cellValue = "";
		// 数据格式
		DataFormatter formatter = new DataFormatter();
		if (cell != null)
		{
			// 单元格类型
			switch (cell.getCellType())
			{
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell))
				{
					// 取值
					cellValue = formatter.formatCellValue(cell);
				}
				else
				{
					// 数字
					double value = cell.getNumericCellValue();
					int intValue = (int) value;
					cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
				}
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				cellValue = String.valueOf(cell.getCellFormula());
				break;
			case Cell.CELL_TYPE_BLANK:
				cellValue = "";
				break;
			case Cell.CELL_TYPE_ERROR:
				cellValue = "";
				break;
			default:
				cellValue = cell.toString().trim();
				break;
			}
		}
		return cellValue.trim();
	}

	/**
	 * 获取文件后缀名
	 * @param path 文件路径
	 * @return 文件后缀名
	 */
	public static String getPostfix(String path)
	{
		if (path == null)
		{
			return "";
		}
		if (path.contains("."))
		{
			return path.substring(path.lastIndexOf(".") + 1, path.length());
		}
		return "";
	}
	
	public static CellStyle cellStyle(Workbook workbook) {
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		Font cellFont = workbook.createFont();
		cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		cellStyle.setFont(cellFont);
		return cellStyle;
	}
	
	public static CellStyle headStyle(Workbook workbook) {
		CellStyle headStyle = workbook.createCellStyle();
		headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		Font headerFont = workbook.createFont();
		headerFont.setFontHeightInPoints((short) 12);
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headStyle.setFont(headerFont);
		return headStyle;
	}

	/**
	 * 导出Excel 2007 OOXML (.xlsx)格式
	 * @param title 标题行
	 * @param headMap 属性-列头
	 * @param jsonArray 数据集
	 * @param datePattern 日期格式，传null值则默认 年月日
	 * @param colWidth 列宽 默认 至少17个字节
	 * @param out 输出流
	 */
	@SuppressWarnings("deprecation")
	public static void exportExcelX(OutputStream out, Map<String, Object> params, List<?> list)
	{
		// 声明一个工作薄
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		// 生成一个(带标题)表格
		XSSFSheet sheet = workbook.createSheet("项目列表");
//		sheet.setDefaultColumnWidth(256*100+184);
		// 行
		XSSFRow titleRow = sheet.createRow(0);
		XSSFCell titleCell = null;
		Set<String> keySet = params.keySet();
		int cell = 0;
		long start = System.currentTimeMillis();
		for (String key : keySet) {
			titleCell = titleRow.createCell(cell);
			if(params.get(key) != null) {
				titleCell.setCellValue(params.get(key).toString());
			}else {
				titleCell.setCellValue("");
			}
			titleCell.setCellStyle(ExcelUtils.headStyle(workbook));
			cell++;
		}
		XSSFRow dataRow = null;
		int row = 1;
		for (Object obj : list) {
			JSONObject parseObject = JSONObject.parseObject(JSONObject.toJSONString(obj));
			dataRow = sheet.createRow(row);
			XSSFCell dataCell = null;
			int valueCell = 0;
			for (String key : keySet) {
				dataCell = dataRow.createCell(valueCell);
				if(key != null) {
					dataCell.setCellValue(parseObject.get(key).toString());
				}
				dataCell.setCellStyle(ExcelUtils.cellStyle(workbook));
				valueCell++;
			}
			row++;
		}
		for(int i = 0; i<=cell; i++) {
			sheet.autoSizeColumn(i);
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		try {
			workbook.write(out);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public static void downloadExcelFile(List<CrmCustom> list, HttpServletResponse response)
//	{
//		try
//		{
//			ByteArrayOutputStream os = new ByteArrayOutputStream();
//			exportExcelX(os, list);
//			byte[] content = os.toByteArray();
//			InputStream is = new ByteArrayInputStream(content);
//			// 设置response参数，可以打开下载页面
//			response.reset();
//
//			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
//			response.setHeader("Content-Disposition", "attachment;filename=" + new String((new Date().getTime() + ".xlsx").getBytes(), "iso-8859-1"));
//			response.setContentLength(content.length);
//			ServletOutputStream outputStream = response.getOutputStream();
//			BufferedInputStream bis = new BufferedInputStream(is);
//			BufferedOutputStream bos = new BufferedOutputStream(outputStream);
//			byte[] buff = new byte[8192];
//			int bytesRead;
//			while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
//			{
//				bos.write(buff, 0, bytesRead);
//			}
//			bis.close();
//			bos.close();
//			outputStream.flush();
//			outputStream.close();
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}

	public static void main(String[] args) throws Exception
	{
		/*OutputStream outXlsx = new FileOutputStream("d:\\temp\\prj.xlsx");
		System.out.println("正在导出xlsx....");
		List<ProjectInfo> prjList = new ArrayList<ProjectInfo>();
		ProjectInfo prj = new ProjectInfo();
		prj.setProjectName("daiwei");
		prj.setProjectCode("yihongwei");
		prjList.add(prj);
		exportExcelX(outXlsx, prjList);
		outXlsx.close();*/

	/*	log.info("解析文件开始...");
		System.out.println("解析文件开始...");
		String fileName = "D:\\temp\\1108.xls";
		Workbook wb = getWorkbook(fileName);*/
		// 拿到第一个sheet
//		Sheet sheet = wb.getSheetAt(0);
		// 循环行，从0开始
//		for (int i = 1; i < 8; i++)
//		{
//			UserInfo user = new UserInfo();
//			log.info("文件行数：" + (i + 1));
//			// 行
//			Row row = sheet.getRow(i);
//			user.setUserId(getCellValue(row.getCell(0)));
//			user.setUserName(getCellValue(row.getCell(1)));
//
//			// 循环列，从0开始
//			for (int j = 0; j < 8; j++)
//			{
//				if (row != null)
//				{
//					String cellValue = getCellValue(row.getCell(j));
//					// System.out.println(cellValue);
//					System.out.println("第" + (i + 1) + "行第" + (j + 1) + "列的值：" + cellValue);
//				}
//
//			}
//
//		}
//		log.info("解析文件结束.");
//		wb.close();
	}

}
