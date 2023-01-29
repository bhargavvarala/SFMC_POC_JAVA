package com.example.demo.DTO;

import java.io.Serializable;

public class Items implements Serializable{
	String name;
	int parentId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	

}
