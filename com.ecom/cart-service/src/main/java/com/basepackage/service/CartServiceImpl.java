package com.basepackage.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import com.basepackage.dto.CartDTO;
import com.basepackage.dto.CartDTO.CartItemDTO;
import com.basepackage.model.Cart;
import com.basepackage.model.Cart.CartItem;
import com.basepackage.repo.AddToCartRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

public class CartServiceImpl implements CartServiceI {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class); 

	@Autowired
private AddToCartRepo addToCartRepo;
	 
	@Autowired
	private ModelMapper   mapper;
	

	
	@Retryable(value= {Exception.class},
			maxAttempts = 3,
			backoff=@Backoff(delay=2000)
			)
	@CircuitBreaker(name = "cartService", fallbackMethod = "fallbackAddToCart")
	@Override
	public Optional<CartDTO> addItemToCart(String userId, CartDTO cartDto,String productId) throws Exception {
		
		if(userId==null||cartDto==null) {
			return  Optional.empty();
		}
		 
		Cart cart = mapper.map(cartDto,Cart.class);

	 List<CartItemDTO> isProdcutPresent=	   cartDto.getItems().stream().filter(item->item.getProductId().equals(productId)).collect(Collectors.toList());
		 
	 if (!isProdcutPresent.isEmpty()) {		
						cartDto.getItems() .stream().filter(item->item.getProductId()==productId) .forEach(item-> item.setQuantity(item.getQuantity()+1));		
					}

					             Cart  addedItem     =addToCartRepo.save(cart);
					
					              if(addedItem!=null&&!addedItem.getCartId().isBlank()) {
					            	  
					            	  return    Optional.of(mapper.map(addedItem,CartDTO.class) ) ;
					            			
					              }	

					return  Optional.empty();
		
				}

	// Fallback used only when Circuit Breaker opens or fails
	public Optional<CartDTO> fallbackAddToCart(String userId, CartDTO cartDto, String productId, Throwable t) {
	    LOGGER.warn("Circuit Breaker fallback triggered due to: {}", t.getMessage());
	    return Optional.empty();
	}
	
	
	@Recover
	public Optional<CartDTO> recoverAddtoCart(Exception e){
		
		LOGGER .warn("Circuit breaker  fallback :{} ",e.getMessage());
		return Optional.empty();
				
	}
 
				@Override
	public  Optional<String>  removeItemFromCart(String userId, String productId,CartDTO cartDto) throws Exception  {
					// TODO Auto-generated method stub
					
					if(userId==null||productId==null) {
						return  Optional.empty();
					}
					
					
					
					Cart cart = mapper.map(cartDto,Cart.class);

					 List<CartItemDTO> isProdcutPresent=	   cartDto.getItems().stream().filter(item->item.getProductId().equals(productId)).collect(Collectors.toList());
						 	
					
					 if(!isProdcutPresent.isEmpty()) {
	cartDto.getItems() .stream().filter(item->item.getProductId()==productId)
	.forEach(item->{ 
		
		if(item.getQuantity()>0)
	 	item.setQuantity(item.getQuantity()-1) ;
	
	          });	
	
	                 Cart updatedCart=addToCartRepo.save(cart);
	                 if(updatedCart.getCartId()!=null)        
	return  Optional.of(" removed the item ");
					 }
					  return Optional.of("no item  left to remove");	
				}
				
				
				

	@Override
	public Optional <CartDTO>moveToWishList(String userID, String productId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public  Optional<String>  clearCart(String userId,CartDTO cartdto) {
		// TODO Auto-generated method stub

		if(userId==null&&cartdto==null) {
			return  Optional.empty();
		}
		
		/*
		 * 
		 * 
		 * List<CartItemDTO> cart= cartdto.getItems();
		 */
		
		
		Cart cartclass = mapper.map(cartdto,Cart.class);

                   addToCartRepo.deleteById(userId);
    
                   if(cartclass.getItems().isEmpty()) {
                	   
                	   return Optional.of(" successfully removed the items ");
                   }
                   
		  return Optional.of("no item  left to remove");	  
	}


	@Override
	public Optional<CartDTO> addItemToCart(String userId, Cart item) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}


	@Override
	public Integer updateItemQuantity(String userId, String productId, int newQuantity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<CartDTO> addItemToCart(String userId, CartDTO cartDto) throws Exception {
		// TODO Auto-generated method stub
		return Optional.empty();
	}


}
