package com.basepackage.repo;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;


import com.basepackage.model.Address;
import com.basepackage.model.Cart;

import com.basepackage.model.ShippingInfo;

public interface ShippingRepo extends MongoRepository<ShippingInfo,String> {


	          
	     ShippingInfo       addAddress(ShippingInfo shippingDTO);
	
	     ShippingInfo    upateAddress(String id,ShippingInfo shippingInfo);
	
	
	     ShippingInfo       upateAddress(String id);
	
	
	
	
	
 }
