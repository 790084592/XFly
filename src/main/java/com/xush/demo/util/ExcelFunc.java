package com.xush.demo.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class ExcelFunc {

	private static ObjectMapper objectMapper;

	@Autowired
	private void setObjectMapper(ObjectMapper objectMapper) {
		ExcelFunc.objectMapper = objectMapper;
	}

	public static ObjectNode analysisExcelByTag(InputStream in, String tag) throws IOException {
		switch (tag) {
			case "xlsx":
				return analysisXlsx(in);
			case "xls":
				return analysisXls(in);
		}
		return null;
	}

	@SuppressWarnings("resource")
	public static ObjectNode analysisXlsx(InputStream in) throws IOException {
		ObjectNode workbookObj = objectMapper.createObjectNode();
		XSSFWorkbook xWorkbook = new XSSFWorkbook(in);
		ArrayNode sheetArray = objectMapper.createArrayNode();
		if (xWorkbook != null) {
			int sheetNums = xWorkbook.getNumberOfSheets();
			for (int i = 0; i < sheetNums; i++) {
				XSSFSheet sheet = xWorkbook.getSheetAt(0);
				sheetArray.add(analysisXSSFSheet(sheet));
			}
			workbookObj.put("type", "xlsx");
			workbookObj.put("sheets", sheetArray);
		}
		xWorkbook.close();
		return workbookObj;
	}

	@SuppressWarnings("resource")
	public static ObjectNode analysisXls(InputStream in) throws IOException {
		ObjectNode workbookObj = objectMapper.createObjectNode();
		HSSFWorkbook hWorkbook = new HSSFWorkbook(in);
		ArrayNode sheetArray = objectMapper.createArrayNode();
		if (hWorkbook != null) {
			int sheetNums = hWorkbook.getActiveSheetIndex();
			for (int i = 0; i < sheetNums; i++) {
				HSSFSheet sheet = hWorkbook.getSheetAt(i);
				sheetArray.add(analysisHSSFSheet(sheet));
			}
			workbookObj.put("type", "xls");
			workbookObj.put("sheets", sheetArray);
		}
		hWorkbook.close();
		return workbookObj;
	}

	public static ObjectNode analysisXSSFSheet(XSSFSheet sheet) throws IOException {
		ObjectNode sheetObj = objectMapper.createObjectNode();
		ArrayNode rowArrays = objectMapper.createArrayNode();
		if (sheet != null) {
			int rowNums = sheet.getLastRowNum();
			for (int i = 0; i < rowNums; i++) {
				XSSFRow row = sheet.getRow(i);
				rowArrays.add(analysisRow(row));
			}
			sheetObj.put("rows", rowArrays);
		}
		return sheetObj;
	}

	public static ObjectNode analysisHSSFSheet(HSSFSheet sheet) throws IOException {
		ObjectNode sheetObj = objectMapper.createObjectNode();
		ArrayNode rowArrays = objectMapper.createArrayNode();
		if (sheet != null) {
			int rowNums = sheet.getLastRowNum();
			for (int i = 0; i < rowNums; i++) {
				HSSFRow row = sheet.getRow(i);
				rowArrays.add(analysisRow(row));
			}
			sheetObj.put("rows", rowArrays);
		}
		return sheetObj;
	}

	public static ObjectNode analysisRow(Row row) throws IOException {
		ObjectNode rowObj = objectMapper.createObjectNode();
		ArrayNode cells = objectMapper.createArrayNode();
		if (row != null) {
			int cellNums = row.getLastCellNum();
			for (int i = 0; i < cellNums; i++) {
				Cell cell = row.getCell(i);
				cells.add(analysisCell(cell));
			}
			rowObj.put("cells", cells);
		}
		return rowObj;
	}

	@SuppressWarnings("deprecation")
	public static ObjectNode analysisCell(Cell cell) throws IOException {
		ObjectNode cellObj = objectMapper.createObjectNode();
		if (cell != null) {
			int type = cell.getCellType();
			switch (type) {
				case Cell.CELL_TYPE_NUMERIC:
					cellObj.put("value", cell.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_STRING:
					cellObj.put("value", cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					cellObj.put("value", cell.getBooleanCellValue());
					break;
			}
		}
		return cellObj;
	}

}
