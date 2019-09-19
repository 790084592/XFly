package com.xush.demo.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
   *    数据源处理
 * 1.跳转到数据源界面
 * @author xush
 * @since  2019年9月19日
 */
@Controller
public class ActionDataSource {
	
	@RequestMapping("/datasource")
	public String toDataSource(){
		return "datasource/datasource";
	}
	

}
