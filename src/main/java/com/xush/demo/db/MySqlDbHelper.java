package com.xush.demo.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cn.hutool.core.util.StrUtil;

/**
 * mysql数据库的方法类
 * @author xush
 *
 */
@Component
@Service("MYSQL") 
public class MySqlDbHelper implements DbHelper {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean checkTableExist(String tableName) {
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
	 * 获取mysql检查库表存在的语句
	 * 
	 * @param tableName
	 * @return
	 */
	public String getCheckTableExistSql(String tableName) {
		String sql = String.format(
				"SELECT count(*) as answer FROM information_schema.TABLES WHERE table_name =upper('%s');", tableName);
		return sql;
	}

	@Override
	public void crateTabel(String sql) throws Exception {
		sql = getCreateDataSourceSql2MySql();
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 获取创建mysql文件数据源表的sql语句
	 * 
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

	@Override
	public String processPageSql(String tableName, String condition, String sortParam, int pageIndex, int pageSize) {
		StringBuffer sf = new StringBuffer();
		sf.append("select * from ").append(tableName);
		//条件
		if(!StrUtil.isNullOrUndefined(condition)) {
			sf.append(" where (").append(condition).append(") ");
		}
		//排序
		if(!StrUtil.isNullOrUndefined(sortParam)) {
			sf.append(" order by ").append(condition);
		}
		//分页
		if(pageIndex >0 && pageIndex >= 0) {
			sf.append(" limit ").append((pageIndex-1)*pageSize).append(",").append(pageSize);
		}
		return sf.toString();
	}
}
