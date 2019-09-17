package com.xush.demo.action.bootstrap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
   *  第三方UI  bootstrap使用示例
 * 1.跳转到bootstrap使用示例界面
 * @author xush
 * @since  2019年9月17日
 */
@Controller
public class ActionBootstrap {

	/**
	   *   跳转到bootstrap使用示例界面
	 * @param model
	 * @return
	 */
	@RequestMapping("/UIDemo")
	public String toBootstrapDemoPage(Model model) {
		model.addAttribute("title", "bootstrap示例");
		return "bootstrap_demo";
	}
}
