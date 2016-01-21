package com.mycompany.ssm.model;

import java.io.Serializable;

/**
 * @author JinBingBing
 *
 */
public class User implements Serializable{
	
	private Integer address;
	private Integer age;
	private String id;
	private String name;
	private String sex;
	public User() {
		super();
	}
	public User(String name){
		this.name = name;
	}

	public Integer getAddress() {
		return address;
	}
	public Integer getAge() {
		return age;
	}

	public String getName() {
		return name;
	}
	public String getSex() {
		return sex;
	}
	public void setAddress(Integer address) {
		this.address = address;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
