package com.example.demo.DTO;

import java.io.Serializable;

public class Views implements Serializable{
	private static final long serialVersionUID = 1L;
	Subjectline subjectline;
	Html html;
	//Text text;
	
	public Html getHtml() {
		return html;
	}

	public void setHtml(Html html) {
		this.html = html;
	}

	public Subjectline getSubjectline() {
		return subjectline;
	}

	public void setSubjectline(Subjectline subjectline) {
		this.subjectline = subjectline;
	}

//	public Text getText() {
//		return text;
//	}
//
//	public void setText(Text text) {
//		this.text = text;
//	}

	@Override
	public String toString() {
		return "Views [html=" + html + "]";
	}

	
	
	
}
