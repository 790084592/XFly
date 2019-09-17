package com.xush.demo.action.orm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xush.demo.orm.demo.DemoEntity;
import com.xush.demo.orm.demo.DemoService;

@Controller
@ComponentScan({ "com.xush.demo.orm.demo" })
@MapperScan("com.xush.demo.orm.demo")
public class IndexAction {
	@Autowired
	private DemoService service;

	@RequestMapping("/orm")
	@ResponseBody
	public String orm() {
		// List list = service.getList();
		DemoEntity entity = service.find(2);
		return entity.toString();
	}

}
