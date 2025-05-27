package com.basepackage.dto;import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public class ProductDTO {
		
		
	    private String id;
	    private String itemName;
	    private String category;
	    private BigDecimal price;
	    private String brand;
	    private String color;
	    private String imageFileId;
	    private String videoFileId;
	    private double rating;
	    private int ratingCount;
	    private Map<String, String> specs;        // detailed specifications
	    private List<String> highlights;          // product highlights or key points
	

    public ProductDTO() {}


	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", itemName=" + itemName + ", category=" + category + ", price=" + price
				+ ", brand=" + brand + ", color=" + color + ", imageFileId=" + imageFileId + ", videoFileId="
				+ videoFileId + ", rating=" + rating + ", ratingCount=" + ratingCount + ", specs=" + specs
				+ ", highlights=" + highlights + "]";
	}


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


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
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


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public int getRatingCount() {
		return ratingCount;
	}


	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}


	public Map<String, String> getSpecs() {
		return specs;
	}


	public void setSpecs(Map<String, String> specs) {
		this.specs = specs;
	}


	public List<String> getHighlights() {
		return highlights;
	}


	public void setHighlights(List<String> highlights) {
		this.highlights = highlights;
	}


	public ProductDTO(String id, String itemName, String category, BigDecimal price, String brand, String color,
			String imageFileId, String videoFileId, double rating, int ratingCount, Map<String, String> specs,
			List<String> highlights) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.category = category;
		this.price = price;
		this.brand = brand;
		this.color = color;
		this.imageFileId = imageFileId;
		this.videoFileId = videoFileId;
		this.rating = rating;
		this.ratingCount = ratingCount;
		this.specs = specs;
		this.highlights = highlights;
	}

    
}

