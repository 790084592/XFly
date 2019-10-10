package com.xush.demo.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cn.hutool.core.util.StrUtil;

/**
 * mysql数据库的方法类
 * 
 * @author xush
 * @since 2019年10月10日
 */
@Component
@Service("ORACLE")
public class OracleDbHelper implements DbHelper {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${spring.datasource.username}")
	private String dataBaseUser;

	@Override
	public boolean checkTableExist(String tableName) throws Exception {
		String sql = getCheckTableExistSql(tableName);
		ArrayList<Integer> list = (ArrayList<Integer>) jdbcTemplate.query(sql, new RowMapper<Integer>() {
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int answer = rs.getInt("answer");
				return (Integer) answer;
			}
		});
		if (list.size() > 0) {
			return list.get(0) > 0;
		}
		return false;
	}

	/**
	 * 获取oracle检查库表存在的语句
	 * 
	 * @param tableName
	 * @return
	 */
	public String getCheckTableExistSql(String tableName) {
		String sql = String.format(
				"select count(*) as answer from user_tables where  TABLE_NAME=upper('%s')", tableName);
		return sql;
	}

	@Override
	public void crateTabel(String sql) throws Exception {
		sql = getCreateDataSourceSql2Oracle();
		jdbcTemplate.execute(sql);
	}

	/**
	 * 获取创建oracle文件数据源表的sql语句
	 * 
	 * @return
	 */
	public String getCreateDataSourceSql2Oracle() {
		StringBuffer sf = new StringBuffer();
		sf.append("create table XFLY_FILEDATASOURCE(");
		sf.append("		id varchar(32) primary key, ");
		sf.append("		name varchar(64) not null, ");
		sf.append("		path varchar(128) not null, ");
		sf.append("		type number(32) not null, ");
		sf.append("		createdate varchar(32) not null, ");
		sf.append("		creator varchar(32) not null ");
		sf.append(")");
		return sf.toString();
	}

	@Override
	public String processPageSql(String tableName, String condition, String sortParam, int pageIndex, int pageSize){
		StringBuffer sf = new StringBuffer();
		sf.append("SELECT *");
		sf.append("FROM (	SELECT tt.*, ROWNUM AS rowno");
		sf.append("		    FROM (	SELECT t.*");
		sf.append("					FROM ").append(tableName).append(" t");
		if (!StrUtil.isNullOrUndefined(condition)) {
			sf.append(" 			WHERE (").append(condition).append(")");
		}
		if (!StrUtil.isNullOrUndefined(sortParam)) {
			sf.append(" 			ORDER BY ").append(sortParam);
		}
		sf.append("					) tt");
		sf.append("		   WHERE ROWNUM <= ").append((pageIndex) * pageSize).append(") table_alias ");
		sf.append("WHERE table_alias.rowno >= ").append((pageIndex - 1) * pageSize + 1);
		return sf.toString();
	}
}
