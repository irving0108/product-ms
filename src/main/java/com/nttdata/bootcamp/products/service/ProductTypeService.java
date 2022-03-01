package com.nttdata.bootcamp.products.service;

import com.nttdata.bootcamp.products.model.ProductType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductTypeService {
	void createProductType(ProductType e);
	Mono<ProductType> findByProductTypeId(Integer id);
	Flux<ProductType> findAllProductType();
	Mono<ProductType> updateProductType(ProductType e);
    Mono<Void> deleteProductType(Integer id);
}
