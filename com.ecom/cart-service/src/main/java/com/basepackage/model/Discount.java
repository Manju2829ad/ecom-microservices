package com.basepackage.model;

import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "discount")
public class Discount {

	
	private String id;
	
	private double discountAmount;
	
	private double discountPercent;
	
	private Instant validFrom;
	
	private Instant 	validTill;
	
	private boolean active ;
	
	
	
}
