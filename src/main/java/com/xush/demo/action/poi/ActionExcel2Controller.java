package com.xush.demo.action.poi;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xush.demo.util.ExcelFunc;

/**
   *   解析excel
 * 1.跳转到POI示例使用页面
 * 2.解析上传的Excel
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
	public ObjectNode excelImport(HttpServletRequest req) throws Exception {
		String tag = req.getParameter("type");
		InputStream in = req.getPart("file").getInputStream();
		ObjectNode excelData = ExcelFunc.analysisExcelByTag(in, tag);
		return excelData;
	}

}