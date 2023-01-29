package com.example.demo.DTO;

import java.io.Serializable;

public class AssetType implements Serializable{

	private static final long serialVersionUID = 1L;
	int id;
	String name;
    String  displayName;
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
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	@Override
	public String toString() {
		return "AssetType [id=" + id + ", name=" + name + ", displayName=" + displayName + "]";
	}
	
    
}
