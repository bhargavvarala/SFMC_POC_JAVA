package com.example.demo.DTO;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class TemplateResponseDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	int id;
	String customerKey;
	String objectID;
	String contentType;
	AssetType assetType;
	String name;
	Owner owner;
	int enterpriseId;
    int memberId;
	FileProperties fileProperties;
	Thumbnail thumbnail;
	Category category;
	Views views;
	String content;
	SharingProperties SharingProperties;
	//String createdDate;
	CreatedBy createdBy;
	ModifiedBy modifiedBy;
	

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

	public TemplateResponseDTO(int id, String customerKey, String objectID, String contentType, AssetType assetType,
			String name, Owner owner, int enterpriseId, int memberId, FileProperties fileProperties,
			Thumbnail thumbnail, Category category, Views views, String content,
			com.example.demo.DTO.SharingProperties sharingProperties, String createdDate, CreatedBy createdBy,
			ModifiedBy modifiedBy) {
		super();
		this.id = id;
		this.customerKey = customerKey;
		this.objectID = objectID;
		this.contentType = contentType;
		this.assetType = assetType;
		this.name = name;
		this.owner = owner;
		this.enterpriseId = enterpriseId;
		this.memberId = memberId;
		this.fileProperties = fileProperties;
		this.thumbnail = thumbnail;
		this.category = category;
		this.views = views;
		this.content = content;
		SharingProperties = sharingProperties;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}
	
	public TemplateResponseDTO() {
		super();
	}

	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerKey() {
		return customerKey;
	}
	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
	}
	public AssetType getAssetType() {
		return assetType;
	}
	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}
	public FileProperties getFileProperties() {
		return fileProperties;
	}
	public void setFileProperties(FileProperties fileProperties) {
		this.fileProperties = fileProperties;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public SharingProperties getSharingProperties() {
		return SharingProperties;
	}
	public void setSharingProperties(SharingProperties sharingProperties) {
		SharingProperties = sharingProperties;
	}
	
	public String getObjectID() {
		return objectID;
	}
	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public int getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Thumbnail getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(Thumbnail thumbnail) {
		this.thumbnail = thumbnail;
	}
	public Views getViews() {
		return views;
	}
	public void setViews(Views views) {
		this.views = views;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "TemplateResponseDTO [id=" + id + ", customerKey=" + customerKey + ", objectID=" + objectID
				+ ", contentType=" + contentType + ", assetType=" + assetType + ", name=" + name + ", owner=" + owner
				+ ", enterpriseId=" + enterpriseId + ", memberId=" + memberId + ", fileProperties=" + fileProperties
				+ ", thumbnail=" + thumbnail + ", category=" + category + ", views=" + views + ", content=" + content
				+ ", SharingProperties=" + SharingProperties + "]";
	}
	
	
}
