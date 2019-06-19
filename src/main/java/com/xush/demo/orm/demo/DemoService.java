package com.xush.demo.orm.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List getList() {

		
		String sql = "select newsId_,location_,userName_,content_ from TravellerNews_list_";
		return (List) jdbcTemplate.query(sql, new RowMapper() {
			public DemoEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
				String id = rs.getString("newsId_");
				System.out.println(id);
				DemoEntity entity = new DemoEntity();
				entity.setAge(1);
				entity.setId("11");
				entity.setName("111");

				return entity;
			}

		});
	}
}
