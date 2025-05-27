package com.basepackage.repo;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.basepackage.dto.ProductDTO;
import com.basepackage.model.Product;

public interface ProductRepo extends JpaRepository<Product, String> {

	
	Optional<List<ProductDTO>>       findByCategory(String category);
	
	
	@Query("SELECT p FROM  Product p Where LOWER(p.name)  LIKE  LOWER(CONCAT('%',:keywrod,'%'))" +
	"OR LOWER(p.description) LIKE LOWER(CONCAT('%',:keyword,'%'))"+
		       "OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%'))")
			
	List<Product> searchProducts(@Param("keyword") String keyword);

	
	
	@Query(
	"SELECT p FROM  Product p WHERE " +
	" (:brand IS NULL OR LOWER(p.brand)=LOWER(:brand )) AND" +
			
" (:minPrice IS NULL OR  (p.price) >= :minPrice ) AND "+
	
          "(:maxPrice IS NULL OR  (p.price) <= :maxPrice) AND " +
         
          "(:minRating IS NULL OR p.rating >= :minRating)")
	
	Optional<Page<ProductDTO>>  filterProducts(
			
			@Param("brand") String brand,
			@Param("minPrice") Double minPrice,
			
			@Param("maxPrice") Double maxPrice,
			
			@Param("minRating") Double minRating,
			org.springframework.data.domain.Pageable pageable
			);
	
	
 }
