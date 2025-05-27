package com.basepackage.dto;

import java.util.Map;

public class ServiceCatalogDTO {

    private String id;
    private String itemName;
    private String category;
    private Map<String, Object> attributes;
    private String imageFileId;
    private String videoFileId;

    public ServiceCatalogDTO() {}

    public ServiceCatalogDTO(String id, String itemName, String category, Map<String, Object> attributes, String imageFileId, String videoFileId) {
        this.id = id;
        this.itemName = itemName;
        this.category = category;
        this.attributes = attributes;
        this.imageFileId = imageFileId;
        this.videoFileId = videoFileId;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getImageFileId() {
        return imageFileId;
    }

    public void setImageFileId(String imageFileId) {
        this.imageFileId = imageFileId;
    }

    public String getVideoFileId() {
        return videoFileId;
    }

    public void setVideoFileId(String videoFileId) {
        this.videoFileId = videoFileId;
    }
}
