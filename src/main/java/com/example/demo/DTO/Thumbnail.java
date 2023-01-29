package com.example.demo.DTO;

import java.io.Serializable;

public class Thumbnail implements Serializable{
	String thumbnailUrl;

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	@Override
	public String toString() {
		return "Thumbnail [thumbnailUrl=" + thumbnailUrl + "]";
	}
	

}
