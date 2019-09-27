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
 * 1.跳转到Excel解析示例页面
 * 2.获取上传的Excel并解析
 * @author xush
 * @since  2019年9月7日
 */
@Controller
public class ActionExcelImport {

	/**
	   *    跳转到Excel解析示例页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/ExcelDemo")
	public String toBootstrapDemoPage(Model model) {
		model.addAttribute("title", "POI示例");
		return "poi/poi_demo";
	}

	/**
	   *   获取上传的Excel并解析
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/excel/import")
	@ResponseBody
	public ObjectNode excelImport(HttpServletRequest req) throws Exception {
		String tag = req.getParameter("type");
		InputStream in = req.getPart("file").getInputStream();
		ObjectNode excelData = null;
		try {
			excelData = ExcelFunc.analysisExcelByTag(in, tag);
		}finally {
			in.close();
		}
		return excelData;
	}

}