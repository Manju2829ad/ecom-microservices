package com.basepackage.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.basepackage.dto.CartDTO;
import com.basepackage.model.Cart;

public interface AddToCartRepo  extends MongoRepository<Cart,String>{

	
	Optional<Cart> findByUserId(String userId) throws Exception ;
    void      removeByUserId(String userId) throws Exception ;
    boolean  existsByUserId(String userId) throws Exception ;

	
	
	
	
}
