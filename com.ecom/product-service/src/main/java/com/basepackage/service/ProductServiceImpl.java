package com.basepackage.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.basepackage.dto.ProductDTO;
import com.basepackage.repo.ProductRepo;

public class ProductServiceImpl  implements ProductServiceI{

	
	@Autowired
	private ProductRepo productRepo;
	
	
	@Autowired
  private ModelMapper mapper;
	
	@Override
	public Optional<ProductDTO> getProuductById(String id) throws Exception {
	
		     
		if(id==null||id.isBlank()) {
			
			return Optional.empty();
		}
		
		
	         Optional<ProductDTO> dto=	productRepo.findById(id).map(obj->mapper.map(obj,ProductDTO.class));
		
		      if(dto.isPresent()) {
		    	  
		    	  return dto;
		      }
	                 
		
		
		return Optional.empty();
	}

	
	@Override
	public Optional<List<ProductDTO>> getProuductByCategory(String category) {

         if(category==null||category.isBlank()) {
			
			return Optional.empty();
		}
	         List<ProductDTO> dto=	productRepo.findByCategory(category)
	        		 .stream().map(obj->mapper.map(obj,ProductDTO.class)).collect(Collectors.toList());
		
		      if(!dto.isEmpty()) {
		    	  
		    	  return  Optional.of(dto);
		      }
	                 
		return Optional.empty();
		
	}
	

	@Override
	public Optional<List<ProductDTO>> getProductBySearch(String search) {
		  if (search == null || search.isBlank()) {
		        return Optional.empty();
		    }

		  
		    List<ProductDTO> result = productRepo.searchProducts(search).stream()
		        .map(product -> mapper.map(product, ProductDTO.class))
		        .collect(Collectors.toList());

		    // Depending on your use case, return:
		    // (1) Single matching item — return first
		    return result.isEmpty() ? Optional.empty() : Optional.of(result);
	}


	@Override

	public          Optional<Page<ProductDTO>>  getProductsByFilter(String brand,Double minPrice,Double maxPrice,Double minRating ) {
	   
		
		Pageable pageable = PageRequest.of(0, 10); // You can modify this or accept it as a parameter

	    Optional<Page<ProductDTO>> page = productRepo.filterProducts(brand, minPrice, maxPrice, minRating, pageable);

	    // Convert Page<Product> to Page<ProductDTO>
	     if(page.isEmpty()) {
	    	 
	    	 return  Optional.empty();
	     } else {	    	 
	    	 return  page;
	     }
	}


	
	
	
	
}
