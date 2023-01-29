package com.example.demo.DTO;

import java.io.Serializable;
import java.util.List;

public class SharingProperties implements Serializable{
	String sharingType;
	List<Integer> sharedWith;
	public String getSharingType() {
		return sharingType;
	}
	public void setSharingType(String sharingType) {
		this.sharingType = sharingType;
	}
	public List<Integer> getSharedWith() {
		return sharedWith;
	}
	public void setSharedWith(List<Integer> sharedWith) {
		this.sharedWith = sharedWith;
	}
	@Override
	public String toString() {
		return "SharingProperties [sharingType=" + sharingType + ", sharedWith=" + sharedWith + "]";
	}
	
}
