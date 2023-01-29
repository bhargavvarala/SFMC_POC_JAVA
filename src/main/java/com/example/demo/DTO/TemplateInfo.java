package com.example.demo.DTO;

import java.io.Serializable;

public class TemplateInfo implements Serializable{

	
	private static final long serialVersionUID = 1L;
	int templateId;
	String sfmcBuId;
	boolean isSharedFolder;
	int categoryID;
	
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public int getTemplateId() {
		return templateId;
	}
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	public String getSfmcBuId() {
		return sfmcBuId;
	}
	public void setSfmcBuId(String sfmcBuId) {
		this.sfmcBuId = sfmcBuId;
	}
	public boolean isSharedFolder() {
		return isSharedFolder;
	}
	public void setSharedFolder(boolean isSharedFolder) {
		this.isSharedFolder = isSharedFolder;
	}
	
}
