/*****************************************************************************
 * 프로그램명  : ExcelUtil.java
 * 설     명  : 엑셀 관련 클래스 util class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.25  LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.util.CellRangeAddress;

import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.util.HSSFColor;



/**
 * Excel처리 클래스
 * @author  LHN
 * @version 1.0
 */
public class ExcelUtil {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(ExcelUtil.class);    

	public short SUCCESS = 0;
	public short ERROR_NOT_FOUND_FILE = -1;
	public short ERROR_NOT_FOUND_SHEET = -2;
	public short ERROR_UNDEFINED_TYPE = -3;
	public short ERROR_UNDEFINED_STRING_TYPE = -4;
	public short ERROR_IOEXCEPTION = -5;
	public short ERROR_OUTOFMEMORY = -6;
	public short ERROR_OTHEREXCEPTION = -7;

	/** Data type - String */
	public String DATA_TYPE_STRING = "STRING";
	/** Data type - Integer */
	public String DATA_TYPE_INTEGER = "INTEGER";
	/** Data type - Double */
	public String DATA_TYPE_DOUBLE = "DOUBLE";

	/**
	 * Excel Upload
	 * @param path 파일경로
	 * @param file 파일명
	 * @param sheetName 시트명
	 * @param startRow 데이터영역 시작위치
	 * @param typeList 컬럼별 데이터타입리스트
	 * @param dataList 데이터 리스트
	 * @return short 업로드 결과값
	 *                SUCCESS: 성공,
	 *                ERROR_NOT_FOUND_FILE:파일에러,
	 *                ERROR_NOT_FOUND_SHEET:시트명 에러,
	 *                ERROR_UNDEFINED_TYPE:타입에러,
	 *                ERROR_IOEXCEPTION:IO에러,
	 *                ERROR_OUTOFMEMORY:메모리 에러
	 *                ERROR_OTHEREXCEPTION:기타에러
	 */
	public short upload(String path, String file, String sheetName,
			 				   int startRow, List<String> typeList, List<List<Object>> dataList) {

		short nRet = SUCCESS;
		String workBookName = path + "/" + file;
		 HSSFWorkbook workBook = null;

		 FileUtil fileUtil = new FileUtil();

		 try{
			 HSSFSheet sheet = null;
			 HSSFRow row = null;
			 HSSFCell cell = null;

			 workBook = new HSSFWorkbook(new FileInputStream(new File(workBookName)));
			 if(workBook == null) {
				 nRet = ERROR_NOT_FOUND_FILE;
				 return nRet;
			 }

			 sheet = workBook.getSheet(sheetName);
			 if(sheet == null) {
				 nRet = ERROR_NOT_FOUND_SHEET;
				 return nRet;
			 }

			 int rows = sheet.getPhysicalNumberOfRows();

			 for(int r=startRow;r<rows;r++){
				 row = sheet.getRow(r);
				 List<Object> rowList = new ArrayList<Object>(rows);

				 int cells = row.getPhysicalNumberOfCells();

				 for(int c=0; c<typeList.size(); c++){
					 cell   =  row.getCell(c);

					 if(cell== null){
						 continue;
					 }

					 try {
						 if(typeList.get(c).equals(DATA_TYPE_STRING)){
							 try{
								 rowList.add(new String(StringUtil.nvl(cell.getStringCellValue()).trim()));
							 }catch(IllegalStateException e) {
								 try{
//									 logger.error("row:"+r+"==cells:"+c);
//									 logger.error(cell.getNumericCellValue());
									 rowList.add(new Long (new Double(cell.getNumericCellValue()).intValue()));
								 }catch(IllegalStateException ex) {
//									 logger.error("row:"+r+"==cells:"+c);
//									 logger.error(cell.getNumericCellValue());
									 rowList.add(new Double(cell.getNumericCellValue()));
								 }

							 }
						 } else if(typeList.get(c).equals(DATA_TYPE_INTEGER)){
							 try{
								 rowList.add(new Long(new Double(cell.getNumericCellValue()).intValue()));
							 }catch(IllegalStateException e) {
								 try{
//									 logger.error("row:"+r+"==cells:"+c);
//									 logger.error(StringUtil.nvl(cell.getStringCellValue()));
									 rowList.add(new Long(StringUtil.nvl(cell.getStringCellValue()).trim()));
								 }catch(NumberFormatException ex) {
//									 logger.error("row:"+r+"==cells:"+c);
//									 logger.error(StringUtil.nvl(cell.getStringCellValue()));
									 rowList.add(new String(StringUtil.nvl(cell.getStringCellValue()).trim()));
								 }
							 }
						 } else if(typeList.get(c).equals(DATA_TYPE_DOUBLE)){
							 try{
								 rowList.add(new Double(cell.getNumericCellValue()));
							 }catch(IllegalStateException e) {
								 try{
//									 logger.error("row:"+r+"==cells:"+c);
//									 logger.error(StringUtil.nvl(cell.getStringCellValue()));
									 rowList.add(new Double(StringUtil.nvl(cell.getStringCellValue()).trim()));
								 }catch(NumberFormatException ex) {
//									 logger.error("row:"+r+"==cells:"+c);
//									 logger.error(StringUtil.nvl(cell.getStringCellValue()));
									 rowList.add(new String(StringUtil.nvl(cell.getStringCellValue()).trim()));
								 }
							 }
						 }
					 } catch (ClassCastException ex) {
						 nRet = ERROR_UNDEFINED_TYPE;
					 }
				}//cell
				dataList.add(rowList);
			}//row

		 }catch(IOException e){
			nRet = ERROR_IOEXCEPTION;
			logger.error(StringUtil.stackTraceToString(e));
		 }catch(OutOfMemoryError e){
			nRet = ERROR_OUTOFMEMORY;
			logger.error(StringUtil.stackTraceToString(e));
		 }catch(Exception e){
			nRet = ERROR_OTHEREXCEPTION;
			logger.error(StringUtil.stackTraceToString(e));

		 } finally {
			// 파일 삭제
			fileUtil.deleteFile(path, file);
			 if ( workBook != null ) {
				 workBook = null;
			 }
		 }

		 return nRet;
	}

	/**
	 * Excel Download
	 * @param path 파일경로
	 * @param file 파일명
	 * @param sheetName 시트명
	 * @param bSummary 요약부분설정유무(Y:설정, N:설정안함)
	 * @param colList 헤더명 리스트
	 * @param dataList 데이터 리스트
	 * @return short 다운로드 결과값<br>
	 *                SUCCESS: 성공,
	 *                ERROR_UNDEFINED_TYPE:타입에러,
	 *                ERROR_UNDEFINED_STRING_TYPE:문자에러,
	 *                ERROR_IOEXCEPTION:IO에러,
	 *                ERROR_OUTOFMEMORY:메모리에러
	 *                ERROR_OTHEREXCEPTION:기타에러
	 */
	 public short download(String path, String file, String sheetName, List<HashMap> excelList) {
		 short nRet = SUCCESS;
		 FileOutputStream fileOut = null;

		 String workBookName = path + "/" + file;
		 HSSFWorkbook workBook  =  null;

		 // 기존 파일 삭제
		 FileUtil fileUtil = new FileUtil();
		 List<?> listFile = fileUtil.listInDir(path, "xls");

		 try{

			 int nRowCnt = 0;
			 HSSFSheet sheet  =  null;
			 HSSFRow row     =  null;
			 HSSFCell cell    =  null;

			 workBook = new HSSFWorkbook();

			 sheet = workBook.getSheet(sheetName);
			 if(sheet == null) {
				sheet = workBook.createSheet(sheetName);
			 }

			 for(int index=0 ; index<excelList.size(); index++) {
				 HashMap map = excelList.get(index);

				 String title = (String) map.get("title");
				 List<List<String>> colList = (List<List<String>>) map.get("colList");
				 List<List<Object>> dataList = (List<List<Object>>) map.get("dataList");
				 List<List<String>> footerList = (List<List<String>>) map.get("footerList");

				 int addStart = -1;
				 int addEnd = -1;

				 //logger.debug("workBookName="+workBookName+"/sheetName="+sheetName);

				 // 헤더 작성
				 HSSFCellStyle styleTitle = workBook.createCellStyle();
				 styleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
				 styleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				 styleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				 styleTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
				 styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				 styleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//				styleTitle.setFillForegroundColor(new HSSFColor(new java.awt.Color(242, 242, 242)));
//				styleTitle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				 styleTitle.setFillForegroundColor(new HSSFColor.GREY_25_PERCENT().getIndex());
				 styleTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

				 HSSFFont fontTitle = workBook.createFont();
				 fontTitle.setFontHeightInPoints((short)10);
				 fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

				 styleTitle.setFont(fontTitle);

				HSSFCellStyle styleData = workBook.createCellStyle();
				styleData.setBorderTop(HSSFCellStyle.BORDER_THIN);
				styleData.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				styleData.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				styleData.setBorderRight(HSSFCellStyle.BORDER_THIN);
				styleData.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				styleData.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				styleData.setWrapText(true);

				HSSFCellStyle styleData2 = workBook.createCellStyle();
				styleData2.setBorderTop(HSSFCellStyle.BORDER_THIN);
				styleData2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				styleData2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				styleData2.setBorderRight(HSSFCellStyle.BORDER_THIN);
				styleData2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
				styleData2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				styleData2.setDataFormat((short)BuiltinFormats.getBuiltinFormat("#,##0"));
				styleData2.setWrapText(true);

				int sum_num = nRowCnt + 1;

				// 제목
				if(!"".equals(title)) {
					row = sheet.createRow(nRowCnt++);
					//sheet.addMergedRegion(new CellRangeAddress(0,0,0,titleList.size()-1));

					HSSFCellStyle styleSum = workBook.createCellStyle();
					HSSFFont fontSum = workBook.createFont();
					fontSum.setFontHeightInPoints((short)14);
					fontSum.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
					styleSum.setFont(fontSum);
					//styleSum.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					styleSum.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					cell = row.createCell(0);
					row.setHeight((short)600);
					cell.setCellStyle(styleSum);
					cell.setCellValue(title);

					sum_num = sum_num + 1;
				}

				// 타이틀 작성
				for(int i=0; i<colList.size(); i++){
					row = sheet.createRow(nRowCnt++);
					List<String> rowData = colList.get(i);
					for(int j=0; j<rowData.size(); j++) {

						String data = rowData.get(j);
						cell = row.createCell(j);
						cell.setCellStyle(styleTitle);
						cell.setCellValue(data);
						if("".equals(data)) {
							addEnd = j;
						} else {
							if((addStart >= 0) && (addEnd > addStart )) {
								sheet.addMergedRegion(new CellRangeAddress(i,i,addStart,addEnd));
							}
							addStart = j;
							addEnd = j;
						}
						//cell size
						sheet.autoSizeColumn(j);
						sheet.setColumnWidth(j, (sheet.getColumnWidth(j)+1024));

					}//cell
					if((addStart >= 0) && (addEnd > addStart )) {
						sheet.addMergedRegion(new CellRangeAddress(i,i,addStart,addEnd));
					}

				}//row

				// 데이터 작성
				HSSFFont fontData = workBook.createFont();
				fontData.setFontHeightInPoints((short)10);
				fontData.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

				styleData.setFont(fontData);
				styleData2.setFont(fontData);

				for(int i=0; i<dataList.size(); i++){
					row = sheet.createRow(nRowCnt++);
					List<Object> rowData = dataList.get(i);
					for(int j=0; j<rowData.size(); j++) {
						Object data = rowData.get(j);
						if(data instanceof String) {
							cell = row.createCell(j);
							if(footerList != null && "num".equals(footerList.get(0).get(j))) {
								cell.setCellType(cell.CELL_TYPE_NUMERIC);
								cell.setCellStyle(styleData2);
								String val = (String)rowData.get(j);
								if (val.indexOf(".") == -1) {
									cell.setCellValue(Long.parseLong(StringUtil.nvl((String)rowData.get(j), "0")));

						        } else {
						        	cell.setCellValue(Double.parseDouble((String)rowData.get(j)));
						        }
							} else {
								cell.setCellType(cell.CELL_TYPE_STRING);
								cell.setCellStyle(styleData);
								cell.setCellValue((String)rowData.get(j));
							}

						} else if(data instanceof Integer) {
							cell = row.createCell(j);
							cell.setCellStyle(styleData);
							cell.setCellValue((Integer)rowData.get(j));
						} else if(data instanceof Double) {
							cell = row.createCell(j);
							cell.setCellStyle(styleData);
							cell.setCellValue((Double)rowData.get(j));
						} else if(data instanceof Short) {
							cell = row.createCell(j);
							cell.setCellStyle(styleData);
							cell.setCellValue((Short)rowData.get(j));
						} else if(data instanceof Float) {
							cell = row.createCell(j);
							cell.setCellStyle(styleData);
							cell.setCellValue((Float)rowData.get(j));
						} else {
							nRet = ERROR_UNDEFINED_TYPE;
							return nRet;
						}
					
						if(i==0){
							sheet.autoSizeColumn(j);
							sheet.setColumnWidth(j, (sheet.getColumnWidth(j)+1024));
						}
						
					}
					
				}
				
				
				// footer
				if(footerList != null) {
					for(int i=0; i<footerList.size(); i++){
						row = sheet.createRow(nRowCnt++);
						List<String> rowData = footerList.get(i);
						for(int j=0; j<rowData.size(); j++) {
							//cell size
							sheet.autoSizeColumn(j);
							sheet.setColumnWidth(j, (sheet.getColumnWidth(j)+2048));
							String data = rowData.get(j);
							cell = row.createCell(j);
							if("num".equals(data)) {
								cell.setCellStyle(styleData2);
								String ref = "";
								int ind = cell.getColumnIndex();
								ref = getCellRefString(ind);
	//							String ref = cell.getReference();
	//							ref = ref.replaceAll(String.valueOf(cell.getRowIndex()+1), "");
								cell.setCellFormula( "SUM("+ref+String.valueOf(sum_num)+":"+ref+String.valueOf(dataList.size()+sum_num)+")" );
							} else {
								cell.setCellStyle(styleData);
								cell.setCellValue(data);
							}

						}//cell

					}//row

				}//if

			 }//for

			
			 
			File work = new File(path);
	        // 디렉토리가 존재 하지 않을 경우 생성
	        if(!work.exists()) {
	        	work.mkdirs();
	        }
	
			//Excel 파일 출력(다운로드)
			fileOut = new FileOutputStream(workBookName);
			workBook.write(fileOut);
	
		}catch(OutOfMemoryError e){
			nRet = ERROR_OUTOFMEMORY;
			logger.error(StringUtil.stackTraceToString(e));
	
		}catch(Exception e){
			nRet = ERROR_OTHEREXCEPTION;
			logger.error(StringUtil.stackTraceToString(e));
	
		} finally {
			try {
				if ( fileOut != null ) {
					fileOut.close();
				}
				if ( workBook != null ) {
					workBook = null;
				}
		}catch(IOException e){
			nRet = ERROR_IOEXCEPTION;
			logger.error(StringUtil.stackTraceToString(e));
		}
	}

	return nRet;
	}


	/**
	 * Excel Download
	 * @param path 파일경로
	 * @param file 파일명
	 * @param sheetName 시트명
	 * @param bSummary 요약부분설정유무(Y:설정, N:설정안함)
	 * @param colList 헤더명 리스트
	 * @param dataList 데이터 리스트
	 * @return short 다운로드 결과값<br>
	 *                SUCCESS: 성공,
	 *                ERROR_UNDEFINED_TYPE:타입에러,
	 *                ERROR_UNDEFINED_STRING_TYPE:문자에러,
	 *                ERROR_IOEXCEPTION:IO에러,
	 *                ERROR_OUTOFMEMORY:메모리에러
	 *                ERROR_OTHEREXCEPTION:기타에러
	 */
	 public short download2(String path, String file, String sheetName, List<HashMap> excelList) {
		 short nRet = SUCCESS;
		 FileOutputStream fileOut = null;

		 String workBookName = path + "/" + file;
		 HSSFWorkbook workBook  =  null;

		 // 기존 파일 삭제
		 FileUtil fileUtil = new FileUtil();
		 List<?> listFile = fileUtil.listInDir(path, "xls");

		 try{

			 int nRowCnt = 0;
			 HSSFSheet sheet  =  null;
			 HSSFRow row     =  null;
			 HSSFCell cell    =  null;

			 workBook = new HSSFWorkbook();

			 sheet = workBook.getSheet(sheetName);
			 if(sheet == null) {
				sheet = workBook.createSheet(sheetName);
			 }

			 for(int index=0 ; index<excelList.size(); index++) {
				 HashMap map = excelList.get(index);

				 String title = (String) map.get("title");
				 List<List<Object>> headerList = (List<List<Object>>) map.get("headerList");
				 List<List<String>> colList = (List<List<String>>) map.get("colList");
				 List<List<Object>> dataList = (List<List<Object>>) map.get("dataList");
				 List<List<String>> footerList = (List<List<String>>) map.get("footerList");

				 int addStart = -1;
				 int addEnd = -1;

				 //logger.debug("workBookName="+workBookName+"/sheetName="+sheetName);

				 HSSFCellStyle styleTitle = workBook.createCellStyle();
				 styleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
				 styleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				 styleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				 styleTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
				 styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				 styleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//					styleTitle.setFillForegroundColor(new HSSFColor(new java.awt.Color(242, 242, 242)));
//					styleTitle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				 styleTitle.setFillForegroundColor(new HSSFColor.GREY_25_PERCENT().getIndex());
				 styleTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

				 HSSFFont fontTitle = workBook.createFont();
				 fontTitle.setFontHeightInPoints((short)10);
				 fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

				 styleTitle.setFont(fontTitle);

				HSSFCellStyle styleData = workBook.createCellStyle();
				styleData.setBorderTop(HSSFCellStyle.BORDER_THIN);
				styleData.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				styleData.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				styleData.setBorderRight(HSSFCellStyle.BORDER_THIN);
				styleData.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				styleData.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

				HSSFCellStyle styleData2 = workBook.createCellStyle();
				styleData2.setBorderTop(HSSFCellStyle.BORDER_THIN);
				styleData2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				styleData2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				styleData2.setBorderRight(HSSFCellStyle.BORDER_THIN);
				styleData2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
				styleData2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				styleData2.setDataFormat((short)BuiltinFormats.getBuiltinFormat("#,##0"));

				int sum_num = nRowCnt + 1;

				// 제목
				if(!"".equals(title)) {
					row = sheet.createRow(nRowCnt++);
					//sheet.addMergedRegion(new CellRangeAddress(0,0,0,titleList.size()-1));

					HSSFCellStyle styleSum = workBook.createCellStyle();
					HSSFFont fontSum = workBook.createFont();
					fontSum.setFontHeightInPoints((short)14);
					fontSum.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
					styleSum.setFont(fontSum);
					//styleSum.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					styleSum.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					cell = row.createCell(0);
					row.setHeight((short)600);
					cell.setCellStyle(styleSum);
					cell.setCellValue(title);

					sum_num = sum_num + 1;
				}

				// 헤더 작성
				HSSFFont fontData = workBook.createFont();
				fontData.setFontHeightInPoints((short)10);
				fontData.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

				styleData.setFont(fontData);
				styleData2.setFont(fontData);

				for(int i=0; i<headerList.size(); i++){
					row = sheet.createRow(nRowCnt++);
					List<Object> rowData = headerList.get(i);
					for(int j=0; j<rowData.size(); j++) {
						//cell size
						sheet.autoSizeColumn(j);
						sheet.setColumnWidth(j, (sheet.getColumnWidth(j)+1024));

						Object data = rowData.get(j);
						if(data instanceof String) {
							cell = row.createCell(j);
							if(footerList != null && "num".equals(footerList.get(0).get(j))) {
								cell.setCellType(cell.CELL_TYPE_NUMERIC);
								cell.setCellStyle(styleData2);
								String val = (String)rowData.get(j);
								if (val.indexOf(".") == -1) {
									cell.setCellValue(Long.parseLong(StringUtil.nvl((String)rowData.get(j), "0")));

						        } else {
						        	cell.setCellValue(Double.parseDouble((String)rowData.get(j)));
						        }
							} else {
								cell.setCellType(cell.CELL_TYPE_STRING);
								cell.setCellStyle(styleData);
								cell.setCellValue((String)rowData.get(j));
							}

						} else if(data instanceof Integer) {
							cell = row.createCell(j);
							cell.setCellStyle(styleData);
							cell.setCellValue((Integer)rowData.get(j));
						} else if(data instanceof Double) {
							cell = row.createCell(j);
							cell.setCellStyle(styleData);
							cell.setCellValue((Double)rowData.get(j));
						} else if(data instanceof Short) {
							cell = row.createCell(j);
							cell.setCellStyle(styleData);
							cell.setCellValue((Short)rowData.get(j));
						} else if(data instanceof Float) {
							cell = row.createCell(j);
							cell.setCellStyle(styleData);
							cell.setCellValue((Float)rowData.get(j));
						} else {
							nRet = ERROR_UNDEFINED_TYPE;
							return nRet;
						}
					}
				}

				// 한 줄 공백
				if(!"".equals(title)) {
					row = sheet.createRow(nRowCnt++);
					//sheet.addMergedRegion(new CellRangeAddress(0,0,0,titleList.size()-1));

					HSSFCellStyle styleSum = workBook.createCellStyle();
					//styleSum.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					styleSum.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					cell = row.createCell(0);
					cell.setCellStyle(styleSum);
					cell.setCellValue("");
				}

				// 타이틀 작성
				for(int i=0; i<colList.size(); i++){
					row = sheet.createRow(nRowCnt++);
					List<String> rowData = colList.get(i);
					for(int j=0; j<rowData.size(); j++) {
						//cell size
						sheet.autoSizeColumn(j);
						sheet.setColumnWidth(j, (sheet.getColumnWidth(j)+1024));

						String data = rowData.get(j);
						cell = row.createCell(j);
						cell.setCellStyle(styleTitle);
						cell.setCellValue(data);
						if("".equals(data)) {
							addEnd = j;
						} else {
							if((addStart >= 0) && (addEnd > addStart )) {
								sheet.addMergedRegion(new CellRangeAddress(i,i,addStart,addEnd));
							}
							addStart = j;
							addEnd = j;
						}
					}//cell
					if((addStart >= 0) && (addEnd > addStart )) {
						sheet.addMergedRegion(new CellRangeAddress(i,i,addStart,addEnd));
					}

				}//row



				// 데이터 작성
				for(int i=0; i<dataList.size(); i++){
					row = sheet.createRow(nRowCnt++);
					List<Object> rowData = dataList.get(i);
					for(int j=0; j<rowData.size(); j++) {
						//cell size
						sheet.autoSizeColumn(j);
						sheet.setColumnWidth(j, (sheet.getColumnWidth(j)+1024));

						Object data = rowData.get(j);
						if(data instanceof String) {
							cell = row.createCell(j);
							if(footerList != null && "num".equals(footerList.get(0).get(j))) {
								cell.setCellType(cell.CELL_TYPE_NUMERIC);
								cell.setCellStyle(styleData2);
								String val = (String)rowData.get(j);
								if (val.indexOf(".") == -1) {
									cell.setCellValue(Long.parseLong(StringUtil.nvl((String)rowData.get(j), "0")));

						        } else {
						        	cell.setCellValue(Double.parseDouble((String)rowData.get(j)));
						        }
							} else {
								cell.setCellType(cell.CELL_TYPE_STRING);
								cell.setCellStyle(styleData);
								cell.setCellValue((String)rowData.get(j));
							}

						} else if(data instanceof Integer) {
							cell = row.createCell(j);
							cell.setCellStyle(styleData);
							cell.setCellValue((Integer)rowData.get(j));
						} else if(data instanceof Double) {
							cell = row.createCell(j);
							cell.setCellStyle(styleData);
							cell.setCellValue((Double)rowData.get(j));
						} else if(data instanceof Short) {
							cell = row.createCell(j);
							cell.setCellStyle(styleData);
							cell.setCellValue((Short)rowData.get(j));
						} else if(data instanceof Float) {
							cell = row.createCell(j);
							cell.setCellStyle(styleData);
							cell.setCellValue((Float)rowData.get(j));
						} else {
							nRet = ERROR_UNDEFINED_TYPE;
							return nRet;
						}
					}
				}

				// footer
				if(footerList != null) {
					for(int i=0; i<footerList.size(); i++){
						row = sheet.createRow(nRowCnt++);
						List<String> rowData = footerList.get(i);
						for(int j=0; j<rowData.size(); j++) {
							//cell size
							sheet.autoSizeColumn(j);
							sheet.setColumnWidth(j, (sheet.getColumnWidth(j)+2048));
							String data = rowData.get(j);
							cell = row.createCell(j);
							if("num".equals(data)) {
								cell.setCellStyle(styleData2);
								String ref = "";
								int ind = cell.getColumnIndex();
								ref = getCellRefString(ind);
	//							String ref = cell.getReference();
	//							ref = ref.replaceAll(String.valueOf(cell.getRowIndex()+1), "");
								cell.setCellFormula( "SUM("+ref+String.valueOf(sum_num)+":"+ref+String.valueOf(dataList.size()+sum_num)+")" );
							} else {
								cell.setCellStyle(styleData);
								cell.setCellValue(data);
							}

						}//cell

					}//row

				}//if

		 }//for

		File work = new File(path);
        // 디렉토리가 존재 하지 않을 경우 생성
        if(!work.exists()) {
        	work.mkdirs();
        }

		//Excel 파일 출력(다운로드)
		fileOut = new FileOutputStream(workBookName);
		workBook.write(fileOut);

	}catch(OutOfMemoryError e){
		nRet = ERROR_OUTOFMEMORY;
		logger.error(StringUtil.stackTraceToString(e));

	}catch(Exception e){
		nRet = ERROR_OTHEREXCEPTION;
		logger.error(StringUtil.stackTraceToString(e));

	} finally {
		try {
			if ( fileOut != null ) {
				fileOut.close();
			}
			if ( workBook != null ) {
				workBook = null;
			}
	}catch(IOException e){
		nRet = ERROR_IOEXCEPTION;
		logger.error(StringUtil.stackTraceToString(e));
	}
 }

 return nRet;
}

    /**
	 * Excel Download
	 * @param path 파일경로
	 * @param file 파일명
	 * @param sheetName 시트명
	 * @param bSummary 요약부분설정유무(Y:설정, N:설정안함)
	 * @param colList 헤더명 리스트
	 * @param dataList 데이터 리스트
	 * @return short 다운로드 결과값<br>
	 *                SUCCESS: 성공,
	 *                ERROR_UNDEFINED_TYPE:타입에러,
	 *                ERROR_UNDEFINED_STRING_TYPE:문자에러,
	 *                ERROR_IOEXCEPTION:IO에러,
	 *                ERROR_OUTOFMEMORY:메모리에러
	 *                ERROR_OTHEREXCEPTION:기타에러
	 */
	 public short download(String path, String file, String sheetName, List<HashMap> excelList, String bigTitle) {
		short nRet = SUCCESS;
		FileOutputStream fileOut = null;

		String workBookName = path + "/" + file;
		HSSFWorkbook workBook  =  null;

		// 기존 파일 삭제
		FileUtil fileUtil = new FileUtil();
		List<?> listFile = fileUtil.listInDir(path, "xls");

		try{

			int nRowCnt = 0;
			HSSFSheet sheet  =  null;
			HSSFRow row     =  null;
			HSSFCell cell    =  null;

			workBook = new HSSFWorkbook();

			sheet = workBook.getSheet(sheetName);
			if(sheet == null) {
			    sheet = workBook.createSheet(sheetName);
			}

			if(!"".equals(bigTitle)) {
				row = sheet.createRow(nRowCnt++);

				HSSFCellStyle styleSum = workBook.createCellStyle();
				HSSFFont fontSum = workBook.createFont();
				fontSum.setFontHeightInPoints((short)16);
				fontSum.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				styleSum.setFont(fontSum);
				styleSum.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				cell = row.createCell(0);
				row.setHeight((short)600);
				cell.setCellStyle(styleSum);
				cell.setCellValue(bigTitle);

			}
			nRowCnt++;


			for(int index=0 ; index<excelList.size(); index++) {
			    HashMap map = excelList.get(index);

				String title = (String) map.get("title");
			    List<List<String>> colList = (List<List<String>>) map.get("colList");
				List<List<Object>> dataList = (List<List<Object>>) map.get("dataList");
				List<List<String>> footerList = (List<List<String>>) map.get("footerList");

				int addStart = -1;
				int addEnd = -1;

				//logger.debug("workBookName="+workBookName+"/sheetName="+sheetName);

				// 헤더 작성
				HSSFCellStyle styleTitle = workBook.createCellStyle();
				styleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
				styleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				styleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				styleTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
				styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				styleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//					styleTitle.setFillForegroundColor(new HSSFColor(new java.awt.Color(242, 242, 242)));
//					styleTitle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				styleTitle.setFillForegroundColor(new HSSFColor.GREY_25_PERCENT().getIndex());
				styleTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

				HSSFFont fontTitle = workBook.createFont();
				fontTitle.setFontHeightInPoints((short)10);
				fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
  			    styleTitle.setFont(fontTitle);

				HSSFCellStyle styleData = workBook.createCellStyle();
				styleData.setBorderTop(HSSFCellStyle.BORDER_THIN);
				styleData.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				styleData.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				styleData.setBorderRight(HSSFCellStyle.BORDER_THIN);
				styleData.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				styleData.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

				HSSFCellStyle styleData2 = workBook.createCellStyle();
				styleData2.setBorderTop(HSSFCellStyle.BORDER_THIN);
				styleData2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				styleData2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				styleData2.setBorderRight(HSSFCellStyle.BORDER_THIN);
				styleData2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
				styleData2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				styleData2.setDataFormat((short)BuiltinFormats.getBuiltinFormat("#,##0"));

				int sum_num = nRowCnt + 1;

				// 제목
				if(!"".equals(title)) {
					row = sheet.createRow(nRowCnt++);
					//sheet.addMergedRegion(new CellRangeAddress(0,0,0,titleList.size()-1));
					HSSFCellStyle styleSum = workBook.createCellStyle();
					HSSFFont fontSum = workBook.createFont();
					fontSum.setFontHeightInPoints((short)14);
					fontSum.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
					styleSum.setFont(fontSum);
					//styleSum.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					styleSum.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					cell = row.createCell(0);
					row.setHeight((short)600);
					cell.setCellStyle(styleSum);
					cell.setCellValue(title);

					sum_num = sum_num + 1;
				}

				// 타이틀 작성
				for(int i=0; i<colList.size(); i++){
					row = sheet.createRow(nRowCnt++);
					List<String> rowData = colList.get(i);
					for(int j=0; j<rowData.size(); j++) {
						//cell size
						sheet.autoSizeColumn(j);
						sheet.setColumnWidth(j, (sheet.getColumnWidth(j)+1024));
						String data = rowData.get(j);
						cell = row.createCell(j);
						cell.setCellStyle(styleTitle);
						cell.setCellValue(data);
						if("".equals(data)) {
							addEnd = j;
						} else {
							if((addStart >= 0) && (addEnd > addStart )) {
								sheet.addMergedRegion(new CellRangeAddress(i,i,addStart,addEnd));
							}
							addStart = j;
							addEnd = j;
						}
					}//cell
					if((addStart >= 0) && (addEnd > addStart )) {
						sheet.addMergedRegion(new CellRangeAddress(i,i,addStart,addEnd));
					}

				}//row

				// 데이터 작성
				HSSFFont fontData = workBook.createFont();
				fontData.setFontHeightInPoints((short)10);
				fontData.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
				styleData.setFont(fontData);
				styleData2.setFont(fontData);

				for(int i=0; i<dataList.size(); i++){
					row = sheet.createRow(nRowCnt++);
					List<Object> rowData = dataList.get(i);
					for(int j=0; j<rowData.size(); j++) {
						Object data = rowData.get(j);
						if(data instanceof String) {
							cell = row.createCell(j);
							if(footerList != null && "num".equals(footerList.get(0).get(j))) {
								cell.setCellType(cell.CELL_TYPE_NUMERIC);
								cell.setCellStyle(styleData2);
								String val = (String)rowData.get(j);
								if (val.indexOf(".") == -1) {
									cell.setCellValue(Long.parseLong(StringUtil.nvl((String)rowData.get(j), "0")));

						        } else {
						        	cell.setCellValue(Double.parseDouble((String)rowData.get(j)));
						        }
							} else {
								cell.setCellType(cell.CELL_TYPE_STRING);
								cell.setCellStyle(styleData);
								cell.setCellValue((String)rowData.get(j));
							}

						} else if(data instanceof Integer) {
							cell = row.createCell(j);
							cell.setCellStyle(styleData);
							cell.setCellValue((Integer)rowData.get(j));
						} else if(data instanceof Double) {
							cell = row.createCell(j);
							cell.setCellStyle(styleData);
							cell.setCellValue((Double)rowData.get(j));
						} else if(data instanceof Short) {
							cell = row.createCell(j);
							cell.setCellStyle(styleData);
							cell.setCellValue((Short)rowData.get(j));
						} else if(data instanceof Float) {
							cell = row.createCell(j);
							cell.setCellStyle(styleData);
							cell.setCellValue((Float)rowData.get(j));
						} else {
							nRet = ERROR_UNDEFINED_TYPE;
							return nRet;
						}
					}
				}

				// footer
				if(footerList != null) {
					for(int i=0; i<footerList.size(); i++){
						row = sheet.createRow(nRowCnt++);
						List<String> rowData = footerList.get(i);
						for(int j=0; j<rowData.size(); j++) {
							//cell size
							sheet.autoSizeColumn(j);
							sheet.setColumnWidth(j, (sheet.getColumnWidth(j)+2048));
							String data = rowData.get(j);
							cell = row.createCell(j);
							if("num".equals(data)) {
								cell.setCellStyle(styleData2);
								String ref = "";
								int ind = cell.getColumnIndex();
								ref = getCellRefString(ind);
		//							String ref = cell.getReference();
		//							ref = ref.replaceAll(String.valueOf(cell.getRowIndex()+1), "");
								cell.setCellFormula( "SUM("+ref+String.valueOf(sum_num)+":"+ref+String.valueOf(dataList.size()+sum_num)+")" );
							} else {
								cell.setCellStyle(styleData);
								cell.setCellValue(data);
							}
						}//cell
					}//row
				}//if

			}//for

			File work = new File(path);
	        // 디렉토리가 존재 하지 않을 경우 생성
	        if(!work.exists()) {
	        	work.mkdirs();
	        }

		    //Excel 파일 출력(다운로드)
		    fileOut = new FileOutputStream(workBookName);
		    workBook.write(fileOut);

		} catch(OutOfMemoryError e){
			nRet = ERROR_OUTOFMEMORY;
			logger.error(StringUtil.stackTraceToString(e));

		} catch(Exception e){
			nRet = ERROR_OTHEREXCEPTION;
			logger.error(StringUtil.stackTraceToString(e));
	
		} finally {
			try {
				if ( fileOut != null ) {
					fileOut.close();
				}
				if ( workBook != null ) {
					workBook = null;
				}
			}catch(IOException e){
				nRet = ERROR_IOEXCEPTION;
				logger.error(StringUtil.stackTraceToString(e));
			}
	    }

	    return nRet;
	}

    private int loadPicture( String path, HSSFWorkbook wb ) throws IOException {
        int pictureIndex;
        FileInputStream fis = null;

        ByteArrayOutputStream bos = null;

        try {
     		fis = new FileInputStream(path);
            bos = new ByteArrayOutputStream( );
            int c;
            while ( (c = fis.read()) != -1) {
                bos.write( c );
            }
            pictureIndex = wb.addPicture( bos.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG  );
        } finally {
            if (fis != null) fis.close();
            if (bos != null) bos.close();
        }
        return pictureIndex;
    }



	private final char[] A2Z =
		 {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S'
		 ,'T','U','V','W','X','Y','Z'};

    /**
     * returns the excel cell number (eg. C11, E4, AD1305 etc.) for this cell.
     */
    public String getCellRefString(int cellNum) {
        StringBuffer retval = new StringBuffer();
        int tempcellnum = cellNum;
        do {
            retval.insert(0, A2Z[tempcellnum%26]);
            tempcellnum = (tempcellnum / 26) - 1;
        } while (tempcellnum >= 0);

        return retval.toString();
    }

}





