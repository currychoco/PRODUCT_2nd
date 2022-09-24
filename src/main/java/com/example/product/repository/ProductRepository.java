package com.example.product.repository;

import java.util.List;

import com.example.product.domain.Product;

public interface ProductRepository {
	Product save(Product product);
	List<Product> findAll();
	Product findByName(String name);
	boolean deleteById(Long id);
}
