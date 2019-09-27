package com.xush.demo.orm.filedatasource;

import java.io.Serializable;

/**
 * 文件数据源实体类
 * @author xush
 * @since  2019年9月25日
 */
public class FileDataSourceEntity implements Serializable {

	/**
	 * xs
	 */
	private static final long serialVersionUID = -580962075283023423L;

	private String id;

	private String name;

	private String path;

	private int type;

	private String createDate;

	private String creator;

	/**
	 * 无参构造函数
	 */
	public FileDataSourceEntity() {

	}

	public FileDataSourceEntity(String id, String name, String path, int type, String createDate, String creator) {
		this.id = id;
		this.name = name;
		this.path = path;
		this.type = type;
		this.createDate = createDate;
		this.creator = creator;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", name:" + name + ", path:" + path + ", type:" + type + ", createDate:"
				+ createDate + ", creator:" + creator + "}";
	}

	

}
