package com.xush.demo.orm.demo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoMapper {

	public void insert(DemoEntity user);

	public void update(DemoEntity user);

	public void delete(int id);

	public DemoEntity find(int id);
}