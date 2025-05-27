package com.basepackage.service;

import java.util.Optional;

import com.basepackage.dto.CartDTO;
import com.basepackage.model.Cart;

public interface CartServiceI  {

	
	 Optional<CartDTO> addItemToCart(String userId,Cart item);
	
	
	 Optional<CartDTO>  moveToWishList(String userID,String productId);
	
	         Integer updateItemQuantity(String userId,String productId,int newQuantity);
	 
	    

		Optional<CartDTO> addItemToCart(String userId, CartDTO cartDto) throws Exception;

		Optional<CartDTO> addItemToCart(String userId, CartDTO cartDto, String productId) throws Exception;

		Optional<String> removeItemFromCart(String userId, String productId, CartDTO cartDto) throws Exception;


		Optional<String> clearCart(String userId, CartDTO cartdto);

	
}
