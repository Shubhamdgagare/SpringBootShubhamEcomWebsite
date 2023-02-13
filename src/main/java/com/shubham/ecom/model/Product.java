package com.shubham.ecom.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

//Entity use for creating table
@Entity

//Getter and setter lombok anotation use for creating getters and setters for bellow variables 
@Getter
@Setter
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
//	TO handle Category object because of many to one relation we have to create DTO file for Product for object handling
	private Category category;
	private double price;
	private double weight;
	private String description;
	private String imageName;
	
}
