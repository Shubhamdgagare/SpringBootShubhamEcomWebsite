package com.sheryians.major.dto;

import lombok.Data;

@Data
public class ProductDTO {
//	Created ProductDTO for handling categories with specific id
	private Long id;
	private String name;
	private int categoryId;
	private double price;
	private double weight;
	private String description;
	private String imageName;
	
}
