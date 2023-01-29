package com.example.demo.DTO;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
@Data
//@Builder
public class TemplateRequestDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	String name;
	Views views;
	AssetType assetType;
	Category category;
	SharingProperties sharingProperties;
	Owner owner;
	CreatedBy createdBy;
	ModifiedBy modifiedBy;
	
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public CreatedBy getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(CreatedBy createdBy) {
		this.createdBy = createdBy;
	}
	public ModifiedBy getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(ModifiedBy modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Views getViews() {
		return views;
	}
	public void setViews(Views views) {
		this.views = views;
	}
	public AssetType getAssetType() {
		return assetType;
	}
	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public SharingProperties getSharingProperties() {
		return sharingProperties;
	}
	public void setSharingProperties(SharingProperties sharingProperties) {
		this.sharingProperties = sharingProperties;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public TemplateRequestDTO() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TemplateRequestDTO [name=" + name + ", views=" + views + ", assetType=" + assetType + ", category="
				+ category + ", sharingProperties=" + sharingProperties + "]";
	}
	
	
	
}
