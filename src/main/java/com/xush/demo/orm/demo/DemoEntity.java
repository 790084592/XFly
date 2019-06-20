package com.xush.demo.orm.demo;

import java.io.Serializable;

/**
 * XXX的实体
 * 
 * @author chengf
 * @since 2018年6月28日
 */
public class DemoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3302108388190270341L;

	private int id;
	private String name;
	private String password;
	private int age;

	public DemoEntity() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
