package com.xush.demo.action;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xush.demo.orm.demo.DemoService;



@RestController
public class IndexAction {
	@Autowired
	private DemoService service;

	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/orm")
	public String orm() {
		List list = service.getList();
		return "index";
	}
	
	
}
