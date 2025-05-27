package com.basepackage.controller;

import java.util.List;
import java.util.Optional;

import org.apache.kafka.clients.admin.NewTopic;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.basepackage.config.SecurityConfig;
import com.basepackage.dto.ProductDTO;
import com.basepackage.service.ProductServiceI;

@RestController
@RequestMapping("/api/products")
public class ProduceProductController {

    private final SecurityConfig securityConfig;

	
	
	
	@Autowired
	private  ProductServiceI productServiceI;
	
    private final NewTopic loginTopic;

    ProduceProductController(NewTopic loginTopic, SecurityConfig securityConfig) {
        this.loginTopic = loginTopic;
        this.securityConfig = securityConfig;
    }

    // 1. Get product by ID (e.g., clicking on an item)
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
    	
    	if (id==null||id.isBlank()){
    		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please enter the id");
    	}
    	
    	 try {
			Optional<ProductDTO> dto=productServiceI.getProuductById(id);
			
			if(dto.isPresent()) {
				
				return  ResponseEntity.status(HttpStatus.OK) .body(dto.get());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("someting went wrong");
		}
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("product not found");
    }

    
    
    // 2. Get products by category
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable String category) {
        
    	
    	if(category==null||category.isBlank()) { 		
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please select the category");
    	}
    	
    	Optional<List<ProductDTO>> dto=productServiceI.getProuductByCategory(category);
    	 
    	if(dto.isPresent()) {
    		
    		return  ResponseEntity.status(HttpStatus.OK).body(dto.get());			
    	}
    	
 return  ResponseEntity.status(HttpStatus .INTERNAL_SERVER_ERROR).body("something went wrong");
    	                	             
    	             
    }

    // 3. Get products by filters (e.g., price range, brand, rating)
    @GetMapping("/filter")
    public ResponseEntity<?> getProductsByFilter(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Double minRating
    ) {
    	
    	if(brand==null&&minPrice==null&&maxPrice==null&&minRating==null) {
    		
    	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please select atleast one option");	 
    	}
    	
    	
        Optional<org.springframework.data.domain.Page<ProductDTO>> dto	= productServiceI.getProductsByFilter(brand, minPrice,maxPrice ,minRating);
    	
        if(dto.isPresent()) {
        	
        	return ResponseEntity.status(HttpStatus.OK).body(dto.get());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("data not available");
        
    }

    // 4. Search products by name or keyword
    @GetMapping("/search")
    public ResponseEntity<?> searchProducts(@RequestParam String keyword) {
        return null;
    }

    
    // 5. Get all products (e.g., default home display)
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return null;
    }

    // 6. Get trending/popular products
    @GetMapping("/trending")
    public ResponseEntity<?> getTrendingProducts() {
        return null;
    }

    
    // 7. Get recently viewed/recommended products
    @GetMapping("/recommended")
    public ResponseEntity<?> getRecommendedProducts() {
        return null;
    }
    
    
}
