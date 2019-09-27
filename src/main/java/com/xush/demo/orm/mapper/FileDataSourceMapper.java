package com.xush.demo.orm.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.xush.demo.orm.entity.FileDataSourceEntity;

@Mapper
public interface FileDataSourceMapper {

	public void insert(FileDataSourceEntity ds);

	public void update(FileDataSourceEntity ds);

	public void delete(String id);

	public FileDataSourceEntity find(String id);

	public int getTotalCounts();
}