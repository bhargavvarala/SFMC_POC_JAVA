package com.example.demo.DTO;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Owner implements Serializable{
	
	Integer id;
	String email;
	String name;
	String userId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Owner [id=" + id + ", email=" + email + ", name=" + name + ", userId=" + userId + "]";
	}


}
