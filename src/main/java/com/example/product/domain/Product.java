package com.example.product.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
	private Long id;
	private String name;
	private String createdBy;

}
