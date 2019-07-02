package com.xush.demo.orm.demo;

import java.io.Serializable;

/**
 * orm测试的实体类
 * @author xush
 * @since  2019年7月2日
 */
public class DemoEntity implements Serializable {

	private static final long serialVersionUID = -3302108388190270341L;

	private int id;

	private String name;

	private String password;

	private int age;

	/**
	 * 无参构造函数
	 */
	public DemoEntity() {

	}

	/**
	 * @param id
	 * @param name
	 * @param password
	 * @param age
	 */
	public DemoEntity(int id, String name, String password, int age) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
	}

	/**  
	 * 获取id  
	 * @return id id 
	 */
	public int getId() {
		return id;
	}

	/**  
	 * 设置id  
	 * @param id id  
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**  
	 * 获取name  
	 * @return name name 
	 */
	public String getName() {
		return name;
	}

	/**  
	 * 设置name  
	 * @param name name  
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**  
	 * 获取password  
	 * @return password password 
	 */
	public String getPassword() {
		return password;
	}

	/**  
	 * 设置password  
	 * @param password password  
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**  
	 * 获取age  
	 * @return age age 
	 */
	public int getAge() {
		return age;
	}

	/**  
	 * 设置age  
	 * @param age age  
	 */
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
