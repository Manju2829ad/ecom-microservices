package com.basepackage.controller;

import java.util.List;
import java.util.Optional;

import org.apache.kafka.clients.admin.NewTopic;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.basepackage.config.SecurityConfig;
import com.basepackage.dto.CartDTO;
import com.basepackage.dto.ProductDTO;
import com.basepackage.service.CartServiceI;
import com.basepackage.service.ProductServiceI;

@RestController
@RequestMapping("/api/cart/")
public class  AddtoCartController {

    private final SecurityConfig securityConfig;

	
	@Autowired
	private CartServiceI cartService;


    AddtoCartController(SecurityConfig securityConfig) {
        this.securityConfig = securityConfig;
    }

	
	@PostMapping("/add")
	public ResponseEntity  addToCart(			@Validated
			@CookieValue(name="userId") String userId,
			@RequestParam String productId,
			@RequestBody 	CartDTO cartdto,
                BindingResult  result) {
		
		try {
			Optional<CartDTO> addeddto  =    cartService.addItemToCart(userId,cartdto);	
			if(addeddto.isPresent()) {				
				return ResponseEntity.status(HttpStatus.OK).body(addeddto);			
			}
			
			return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
		}			
	}
	
	
	
	
	@PostMapping("/remove")
	public ResponseEntity  removeFromCart(@Validated   
			
			@CookieValue(name="userId") String  userId,
	        @RequestParam String productId,
			@RequestBody CartDTO  cartdto, BindingResult result) {
		

		try {
			Optional<String> addeddto  =    cartService.removeItemFromCart(userId, productId, cartdto)	;
			if(addeddto.isPresent()) {				
				return ResponseEntity.status(HttpStatus.OK).body(addeddto.get());			
			}
			
			return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unable to remove the item");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
		}			
		
	}
	
	
	@PostMapping("/removeall")
	public ResponseEntity  removeAllFromCart(@Validated 
			
			@CookieValue(name="userId") String userId,
			@RequestBody CartDTO cartdto,
                    BindingResult result) {
		
		
		
		try {
			
			Optional<String> addeddto  =   cartService.clearCart(userId, cartdto);
			
			
			if(addeddto.isPresent()) {
				
				return ResponseEntity.status(HttpStatus.OK).body(addeddto.get());
			}

			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");

		
		
		
	}
}
