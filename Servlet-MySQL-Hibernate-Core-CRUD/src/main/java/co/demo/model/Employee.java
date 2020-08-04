package co.demo.model;

import java.time.LocalDateTime;

public class Employee {

	private int id;
	private String name;
	private String mobile;
	private LocalDateTime createdAt;

	public Employee() {
		super();
	}

	public Employee(String name, String mobile, LocalDateTime createdAt) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.createdAt = createdAt;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
