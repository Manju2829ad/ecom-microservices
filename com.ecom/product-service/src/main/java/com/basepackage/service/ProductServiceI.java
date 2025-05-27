package com.basepackage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.basepackage.dto.ProductDTO;

public interface ProductServiceI  {

	 
	Optional<ProductDTO> getProuductById(String id) throws Exception;
	
	
	Optional<List<ProductDTO>> getProuductByCategory(String category);
	
	Optional<List<ProductDTO>> getProductBySearch(String  search);
	
	
	Optional<Page<ProductDTO>> getProductsByFilter(String brand,Double minPrice,Double maxPrice,Double minRating );
	
	
	
	
	
}
 