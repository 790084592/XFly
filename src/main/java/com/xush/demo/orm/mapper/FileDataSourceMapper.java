package com.xush.demo.orm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.xush.demo.orm.entity.FileDataSourceEntity;

@Mapper
public interface FileDataSourceMapper {

	public void insert(FileDataSourceEntity ds);

	public void update(FileDataSourceEntity ds);

	public void delete(String id);

	public FileDataSourceEntity find(String id);

	public int getTotalCounts();
	
	public List<FileDataSourceEntity> queryList(Map<String, Object> data);
}