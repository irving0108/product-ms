package com.nttdata.bootcamp.products.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.products.model.ProductType;
import com.nttdata.bootcamp.products.repository.ProductTypeRepository;
import com.nttdata.bootcamp.products.service.ProductTypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductTypeServiceImpl implements ProductTypeService{
	
	@Autowired
	ProductTypeRepository productRepository;

	@Override
	public void createProductType(ProductType e) {
		productRepository.save(e).subscribe();
	}

	@Override
	public Mono<ProductType> findByProductTypeId(Integer id) {
		return productRepository.findById(id);
	}

	@Override
	public Flux<ProductType> findAllProductType() {
		return productRepository.findAll();
	}

	@Override
	public Mono<ProductType> updateProductType(ProductType e) {
		return productRepository.save(e);
	}

	@Override
	public Mono<Void> deleteProductType(Integer id) {
		return productRepository.deleteById(id);
	}

}
