package com.example.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductReadDto {
	private Long id;
	private String name;
	private String createdBy;
}
