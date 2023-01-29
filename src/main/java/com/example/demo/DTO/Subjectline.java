package com.example.demo.DTO;

import java.io.Serializable;

public class Subjectline implements Serializable{
	String content;
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Subjectline [content=" + content + "]";
	}
	

}
