package com.example.demo.DTO;

import java.io.Serializable;
import java.util.List;

public class FolderResponseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	List<Items> items;
	public List<Items> getItems() {
		return items;
	}
	public void setItems(List<Items> items) {
		this.items = items;
	}
	
}
