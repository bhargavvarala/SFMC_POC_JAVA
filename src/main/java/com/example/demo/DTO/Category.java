package com.example.demo.DTO;

import java.io.Serializable;

public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	int id;
    //String parentId;
   // String name;
    
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
//	public String getParentId() {
//		return parentId;
//	}
//	public void setParentId(String parentId) {
//		this.parentId = parentId;
//	}
	@Override
	public String toString() {
		return "Category [id=" + id + "]";
	}

}
