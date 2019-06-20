package com.xush.demo.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xush.demo.threadpool.XThreadPool;

@Controller
public class HomeAction {

	@RequestMapping("/home")
	public String getIndexPage(Model model) {
		model.addAttribute("name", "首页");
		XThreadPool xthreadpool = XThreadPool.getInstance();
		xthreadpool.addTask("1");
		return "homepage";
	}

}
