package com.xush.demo.controller;

import java.text.ParseException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.xush.demo.util.JdbcFunc;

/**
 * 项目系统表建立与检测
 * @author xush
 * @since  2019年9月26日
 */
@Component
public class TableCreator {
	
	@Autowired
  private JdbcTemplate jdbcTemplate;

	/**
	    *       配置系统表
	 * @throws ParseException 
	 */
	@PostConstruct
	public void init() throws ParseException {
		boolean exist = JdbcFunc.checkTableExist("XFLY_FILEDATASOURCE", jdbcTemplate);
		if(!exist) {
			StringBuffer sf = new StringBuffer();
			sf.append("create table XFLY_FILEDATASOURCE(");
			sf.append("		id varchar(32) primary key, ");
			sf.append("		name varchar(64) not null, ");
			sf.append("		path varchar(128) not null, ");
			sf.append("		type number(32) not null, ");
			sf.append("		createdate varchar(32) not null, ");
			sf.append("		creator varchar(32) not null ");
			sf.append(")");
			JdbcFunc.createTable(sf.toString(), jdbcTemplate);
		}
	}

}
