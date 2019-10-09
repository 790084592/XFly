package com.xush.demo.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * 项目系统表建立与检测
 * @author xush
 * @since 2019年9月26日
 */
@Component
public class TableCreator {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${xfly.datasource.type}")
	private String dataBaseType;
	
	@Value("${spring.datasource.username}")
	private String dataBaseUser;

	public static final String MYSQL = "MYSQL";

	public static final String ORACLE = "ORACLE";
	

	/**
	 * 配置系统表
	 * @throws ParseException
	 */
	@PostConstruct
	public void init() throws ParseException {
		boolean exist = checkTableExist("XFLY_FILEDATASOURCE");
		if (!exist) {
			createDataSourceTable();
		}
	}

	/**
	 * 创建文件数据源数据库表
	 */
	public void createDataSourceTable() {
		String sql = "";
		switch (dataBaseType) {
		case MYSQL:
			sql = getCreateDataSourceSql2Oracle();
			break;
		case ORACLE:
			sql = getCreateDataSourceSql2MySql();
			break;
		}
		jdbcTemplate.execute(sql);
	}

	/**
	 * 获取创建mysql文件数据源表的sql语句
	 * @return
	 */
	public String getCreateDataSourceSql2MySql() {
		StringBuffer sf = new StringBuffer();
		sf.append("create table XFLY_FILEDATASOURCE(");
		sf.append("		id varchar(32) primary key, ");
		sf.append("		name varchar(64) not null, ");
		sf.append("		path varchar(128) not null, ");
		sf.append("		type int(32) not null, ");
		sf.append("		createdate varchar(32) not null, ");
		sf.append("		creator varchar(32) not null ");
		sf.append(")");
		return sf.toString();
	}

	/**
	 * 获取创建oracle文件数据源表的sql语句
	 * @return
	 */
	public String getCreateDataSourceSql2Oracle() {
		StringBuffer sf = new StringBuffer();
		sf.append("create table XFLY_FILEDATASOURCE(");
		sf.append("		id varchar(32) primary key, ");
		sf.append("		name varchar(64) not null, ");
		sf.append("		path varchar(128) not null, ");
		sf.append("		type int(32) not null, ");
		sf.append("		createdate varchar(32) not null, ");
		sf.append("		creator varchar(32) not null ");
		sf.append(")");
		return sf.toString();
	}

	/**
	 * 检查数据库表是否存在
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean checkTableExist(String tableName) {
		String sql = "";
		switch (dataBaseType) {
		case MYSQL:
			sql = getCheckTableExistSql2MySql(tableName);
			break;
		case ORACLE:
			sql = getCheckTableExistSql2Oracle(tableName);
			break;
		}
		ArrayList<Integer> list = (ArrayList<Integer>) jdbcTemplate.query(sql, new RowMapper() {
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int answer = rs.getInt("answer");
				return answer;
			}
		});
		if (list.size() > 0) {
			return list.get(0) > 0;
		}
		return false;
	}

	/**
	 * 获取mysql检查库表存在的语句
	 * @param tableName
	 * @return
	 */
	public String getCheckTableExistSql2MySql(String tableName) {
		String sql = String.format(
				"SELECT count(*) as answer FROM information_schema.TABLES WHERE table_name =upper('%s');", tableName);
		return sql;
	}

	/**
	 * 获取oracle监测库表存在的语句
	 * @param tableName
	 * @return
	 */
	public String getCheckTableExistSql2Oracle(String tableName) {
		String sql = String.format(
				"select count(*) as answer from INFORMATION_SCHEMA.TABLES whereTABLE_SCHEMA='%s' and TABLE_NAME=upper('%s')",
				dataBaseUser, tableName);
		return sql;
	}

}
