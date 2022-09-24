package com.example.product.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.product.domain.Product;
import com.example.product.dto.ProductCreateDto;
import com.example.product.dto.ProductReadDto;
import com.example.product.service.ProductService;

@Controller
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping(value = "/product/new")
	public String createForm() {
		return "product/createProductForm";
	}

	@PostMapping(value = "/product/new")
	public String create(ProductCreateDto productCreateDto) {
		Product product = new Product();
		product.setName(productCreateDto.getName());
		productService.addProduct(product);
		return "redirect:/product/new";
	}
	
	@GetMapping(value = "/product")
	public String list(Model model) {
		List<ProductReadDto> products = productService.findProducts();
		model.addAttribute("products",products);
		return "product/productList";
	}
	
	@GetMapping(value = "/product/{id}/delete")
	public String delete(@PathVariable(name = "id") long id) {
		productService.deleteById(id);
		return "redirect:/product";
	}
}
