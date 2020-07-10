package com.tony.tt.vo;

public class User {
	
	private int id;
	private String name;
	private String gender;
	private int age;
	private String phoneNo;
	private String addr;
	
	@Override
	public String toString() {
		return "User [name=" + name + ", gender=" + gender + ", age=" + age + ", phoneNo=" + phoneNo + ", addr=" + addr
				+ "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
