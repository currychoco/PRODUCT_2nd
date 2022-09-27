package com.example.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.product.domain.Product;
import com.example.product.dto.ProductReadDto;
import com.example.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public Long addProduct(Product product) {
		validateDuplicateProduct(product);
		productRepository.save(product);
		return product.getId();
	}
	
	public void validateDuplicateProduct(Product product) {
		Product tmp = productRepository.findByName(product.getName());
		if(tmp != null) {
			throw new IllegalStateException("이미 존재하는 상품입니다.");
		}
	}
	
	public List<ProductReadDto> findProducts(){
		List<Product> products = productRepository.findAll();
		List<ProductReadDto> dtos = new ArrayList<>();
		for(Product product : products) {
			ProductReadDto dto = new ProductReadDto();
			dto.setId(product.getId());
			dto.setName(product.getName());
			dto.setCreatedBy(product.getCreatedBy());
			dtos.add(dto);
		}
		return dtos;
	}
	
	public void deleteById(Long id) {
		boolean deleteSuccess = productRepository.deleteById(id);
		if(!deleteSuccess) {
			throw new IllegalStateException("해당 id가 존재하지 않습니다.");
		}
	}
}
