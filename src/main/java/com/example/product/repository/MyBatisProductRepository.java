package com.example.product.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.example.product.domain.Product;

@Repository
public class MyBatisProductRepository implements ProductRepository {

	private final SqlSessionTemplate sqlSession;

	public MyBatisProductRepository(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Product save(Product product) {
		sqlSession.insert("product.save", product);
		return product;
	}

	@Override
	public List<Product> findAll() {
		return sqlSession.selectList("product.findAll");
	}

	@Override
	public Product findByName(String name) {
		return sqlSession.selectOne("product.findByName", name);
	}

	@Override
	public boolean deleteById(Long id) {
		int rowCount = sqlSession.delete("product.deleteById", id);
		return rowCount > 0;
	}
}
