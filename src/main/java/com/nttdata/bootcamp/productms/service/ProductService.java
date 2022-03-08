package com.nttdata.bootcamp.productms.service;

import com.nttdata.bootcamp.productms.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
	Mono<String> createProduct(Product e);
	Mono<Product> findByProductId(String id);
	Flux<Product> findByCustomerId(int id);
	Flux<Product> findAllProduct();
	Mono<Product> updateProduct(Product e);
    Mono<Void> deleteProduct(String id);
}
