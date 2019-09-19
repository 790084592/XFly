package com.xush.demo.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xush.demo.threadpool.XThreadPool;

/**
   *   主页面
 * 1.跳转到主页面
 * @author xush
 * @since  2019年9月17日
 */
@Controller
public class ActionMain {

	/**
	   *   跳转到主页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/home")
	public String getIndexPage(Model model) {
		model.addAttribute("name", "首页");
		XThreadPool xthreadpool = XThreadPool.getInstance();
		xthreadpool.addTask("1");
		return "mainframe";
	}
}
