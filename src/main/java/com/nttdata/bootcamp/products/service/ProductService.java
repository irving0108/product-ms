package com.nttdata.bootcamp.products.service;

import com.nttdata.bootcamp.products.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
	void createProduct(Product e);
	Mono<Product> findByProductId(String id);
	Flux<Product> findByCustomerId(int id);
	Flux<Product> findAllProduct();
	Mono<Product> updateProduct(Product e);
    Mono<Void> deleteProduct(String id);
}
