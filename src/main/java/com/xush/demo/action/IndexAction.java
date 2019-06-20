package com.xush.demo.action;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xush.demo.orm.demo.DemoEntity;
import com.xush.demo.orm.demo.DemoService;



@RestController
@ComponentScan({"com.xush.demo.orm.demo"})
@MapperScan("com.xush.demo.orm.demo")
public class IndexAction {
	@Autowired
	private DemoService service;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/orm")
	public String orm() {
		//List list = service.getList();
		DemoEntity entity = service.find(1);
		
		return "index";
	}
	
	
}
