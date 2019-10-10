package com.xush.demo.db;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 项目系统表建立与检测
 * 
 * @author xush
 * @since 2019年9月26日
 */
@Component
public class DataBaseFactory {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 数据库类型
	@Value("${xfly.datasource.type}")
	private static String dataBaseType;

	@Value("${spring.datasource.username}")
	private static String dataBaseUser;

	// 数据库工具类
	private static DbHelper dbHelper;

	@Autowired
	@Qualifier(DbConst.MYSQL)
	private MySqlDbHelper mySqlDbHelper;

	@Autowired
	@Qualifier(DbConst.ORACLE)
	private OracleDbHelper oracleDbHelper;

	// @Value的注入会在@Autowired的注入之后，因此仍需获取数据库类型
	@Autowired
	public void setDbHelper(@Value("${xfly.datasource.type}") String type) {
		switch (type) {
		case DbConst.MYSQL:
			dbHelper = mySqlDbHelper;
			break;
		case DbConst.ORACLE:
			dbHelper = oracleDbHelper;
			break;
		}
	}

	/**
	 * 配置系统表
	 * 
	 * @throws Exception
	 */
	@PostConstruct
	public void init() {
		try {
			boolean exist = dbHelper.checkTableExist("XFLY_FILEDATASOURCE");
			System.out.println("===" + exist);
			if (!exist) {
				//dbHelper.crateTabel("");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public static DbHelper getDbHelper() {
		return dbHelper;
	}
	
	public static String getDbType() {
		return dataBaseType;
	}

}
