package com.xush.demo.orm.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.xush.demo.db.DataBaseFactory;
import com.xush.demo.db.DbConst;
import com.xush.demo.db.DbHelper;
import com.xush.demo.orm.entity.FileDataSourceEntity;
import com.xush.demo.orm.mapper.FileDataSourceMapper;

@ComponentScan({ "com.xush.demo.orm.filedatasource" })
@Service
public class FileDataSourceService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Resource
	private FileDataSourceMapper FileDataSourceMapper;

	public void insert(FileDataSourceEntity ds) {
		FileDataSourceMapper.insert(ds);
	}

	public void update(FileDataSourceEntity ds) {
		FileDataSourceMapper.update(ds);
	}

	public FileDataSourceEntity find(String id) {
		return FileDataSourceMapper.find(id);
	}

	public void delete(String id) {
		FileDataSourceMapper.delete(id);
	}

	public ArrayList<FileDataSourceEntity> listDatas(int pageIndex, int pageSize) {
		DbHelper dbHelper = DataBaseFactory.getDbHelper();
		//mysql数据库可以直接用mybatis
		if(DbConst.MYSQL.equals(DataBaseFactory.getDbType())) {
			Map<String, Object> data = new HashMap<String, Object> ();
			data.put("currIndex", (pageIndex-1)*pageSize);
			data.put("pageSize",  pageSize);
			return (ArrayList<FileDataSourceEntity>) FileDataSourceMapper.queryList(data);
		}
		String sql = dbHelper.processPageSql("XFLY_FILEDATASOURCE", null, null, pageIndex, pageSize);
		ArrayList<FileDataSourceEntity> list = (ArrayList<FileDataSourceEntity>) jdbcTemplate.query(sql,
				new RowMapper<FileDataSourceEntity>() {
					public FileDataSourceEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
						FileDataSourceEntity entity = new FileDataSourceEntity();
						String id = rs.getString("id");
						String name = rs.getString("name");
						int type = rs.getInt("type");
						String path = rs.getString("path");
						String createDate = rs.getString("createDate");
						String creator = rs.getString("creator");
						entity.setId(id);
						entity.setName(name);
						entity.setType(type);
						entity.setPath(path);
						entity.setCreateDate(createDate);
						entity.setCreator(creator);
						return entity;
					}
				});
		return list;
	}

	public int getTotalCounts() {
		return FileDataSourceMapper.getTotalCounts();
	}

}
