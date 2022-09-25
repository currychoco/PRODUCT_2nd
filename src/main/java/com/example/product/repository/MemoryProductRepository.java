package com.example.product.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.product.domain.Product;

public class MemoryProductRepository implements ProductRepository{
	private static Map<Long, Product> store = new HashMap<>();
	private static long sequence = 0L;
	
	@Override
	public Product save(Product product) {
		product.setId(++sequence);
		store.put(sequence, product);
		return product;
	}

	@Override
	public List<Product> findAll() {
		return new ArrayList<Product>(store.values());
	}

	@Override
	public Product findByName(String name) {
		List<Product> allProducts = new ArrayList<Product>(store.values());
		for(Product product : allProducts) {
			if(product.getName().equals(name)) {
				return product;
			}
		}
		return null;
	}

	@Override
	public boolean deleteById(Long id) {
		Product p = store.remove(id);
		return p != null;
	}
	

}
