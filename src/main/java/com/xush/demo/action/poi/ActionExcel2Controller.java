package com.xush.demo.action.poi;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 解析excel
 * @author xush
 * @since  2019年9月7日
 */
@Controller
public class ActionExcel2Controller {

	@RequestMapping("/poiExcelDemo")
	public String toBootstrapDemoPage(Model model) {
		model.addAttribute("title", "POI示例");
		return "poi_demo";
	}

	//上传文件的接口
	@RequestMapping(value = "/excel/import")
	@ResponseBody
	public String excelImport(HttpServletRequest req) throws Exception {

		Collection<Part> parts = req.getParts();
		InputStream fis = null;
		for (Part part : parts) {
			fis = part.getInputStream();
		}
		read2007Xlsx(fis);
		return "ok";
	}

	/**
	 * 读取2007版本之后的excel 即xlsx后缀的excel
	 * @param in
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws ParseException
	 */
	public static List<HashMap<String, String>> read2007Xlsx(InputStream in)
			throws IOException, InvalidFormatException, ParseException {
		List<HashMap<String, String>> list = new ArrayList<>();
		XSSFWorkbook xWorkbook = new XSSFWorkbook(in);
		//读取第一个分页
		XSSFSheet xssfSheet = xWorkbook.getSheetAt(0);
		//遍历行
		for (int rowNum = 1, rowLens = xssfSheet.getPhysicalNumberOfRows(); rowNum <= rowLens; rowNum++) {
			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
			if (xssfRow != null) {
				int cellNums = xssfRow.getPhysicalNumberOfCells();
				//遍历列
				for (int i = 0; i < cellNums; i++) {
					XSSFCell cell = xssfRow.getCell(i);
					System.out.print(cell.getRawValue() + " ");
				}
				System.out.println(" ");
			}
		}
		xWorkbook.close();
		return list;
	}

}