package com.xush.demo.orm.demo;

import javax.annotation.Resource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@ComponentScan({ "com.xush.demo.orm.demo" })
@Service
public class DemoService {

//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//
//	public List getList() {
//
//		String sql = "select newsId_,location_,userName_,content_ from TravellerNews_list_";
//		return (List) jdbcTemplate.query(sql, new RowMapper() {
//			public DemoEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
//				String id = rs.getString("newsId_");
//				System.out.println(id);
//				DemoEntity entity = new DemoEntity();
//				entity.setAge(1);
//				entity.setId(11);
//				entity.setPassword("xs123456");
//				entity.setName("111");
//
//				return entity;
//			}
//
//		});
//	}

	@Resource
	private DemoMapper DemoMapper;

	public void insert(DemoEntity user) {
		DemoMapper.insert(user);
	}

	public void update(DemoEntity user) {
		DemoMapper.update(user);
	}

	public DemoEntity find(int id) {
		return DemoMapper.find(id);
	}

	public void delete(int id) {
		DemoMapper.delete(id);
	}
}
