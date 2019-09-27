package com.xush.demo.orm.filedatasource;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileDataSourceMapper {
	
	public void insert(FileDataSourceEntity ds);

	public void update(FileDataSourceEntity ds);

	public void delete(String id);

	public FileDataSourceEntity find(String id);
	
	public int getTotalCounts();
}