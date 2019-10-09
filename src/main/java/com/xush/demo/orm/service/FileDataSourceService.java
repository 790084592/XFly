package com.xush.demo.orm.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<FileDataSourceEntity> listDatas(int pageIndex, int pageSize) {
		StringBuffer sf = new StringBuffer();
		sf.append("SELECT *");
		sf.append("FROM (	SELECT tt.*, ROWNUM AS rowno");
		sf.append("		 FROM (	SELECT t.*");
		sf.append("						FROM XFLY_FILEDATASOURCE t");
		//		sf.append("							WHERE hire_date BETWEEN TO_DATE ('20060501', 'yyyymmdd')");
		//		sf.append("									AND TO_DATE ('20060731', 'yyyymmdd')");
		sf.append("						ORDER BY createDate DESC) tt");
		sf.append("		 WHERE ROWNUM <= ").append((pageIndex) * pageSize).append(") table_alias ");
		sf.append("WHERE table_alias.rowno >= ").append((pageIndex - 1) * pageSize + 1);
		System.out.println(sf.toString());
		ArrayList<FileDataSourceEntity> list = (ArrayList<FileDataSourceEntity>) jdbcTemplate.query(sf.toString(),
				new RowMapper() {
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
