package com.basepackage.dto;

import lombok.Data;
import java.time.Instant;
import java.util.List;

@Data
public class CartDTO {
    public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<CartItemDTO> getItems() {
		return items;
	}

	public void setItems(List<CartItemDTO> items) {
		this.items = items;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public ShippingInfoDTO getShippingInfo() {
		return shippingInfo;
	}

	public void setShippingInfo(ShippingInfoDTO shippingInfo) {
		this.shippingInfo = shippingInfo;
	}

	public DiscountDTO getDiscount() {
		return discount;
	}

	public void setDiscount(DiscountDTO discount) {
		this.discount = discount;
	}

	private String cartId;
    private String userId;
    private List<CartItemDTO> items;
    private Instant createdAt;
    private Instant updatedAt;
    private ShippingInfoDTO shippingInfo;
    private DiscountDTO discount;

    // Inner DTO class for CartItem
    @Data
    public static class CartItemDTO {
        private String productId;
        private int quantity;
        private double price;
		public String getProductId() {
			return productId;
		}
		public void setProductId(String productId) {
			this.productId = productId;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
    }
}
