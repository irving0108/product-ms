package com.nttdata.bootcamp.productms.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.productms.bean.Customer;
import com.nttdata.bootcamp.productms.model.Product;
import com.nttdata.bootcamp.productms.repository.ProductRepository;
import com.nttdata.bootcamp.productms.restClient.RestHttpClient;
import com.nttdata.bootcamp.productms.service.ProductService;
import com.nttdata.bootcamp.productms.service.ProductTypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	RestHttpClient restClient;
	
	@Autowired
	ProductTypeService productTypeService;
	
	@Value("${customer.product.type.id}")
	private int[] idProductsType;
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Mono<String> createProduct(Product e) {
		Mono<Customer> tmpCustomer = restClient.getCustomerById(e.getIdCustomer());
		if(!Arrays.asList(idProductsType).contains(e.getIdProduct())) {
			return Mono.just("idProduct invalid!.");
		}
		return tmpCustomer.map(response -> {
			productRepository.save(e).subscribe();
			return "Product created successfully!!!.";
		}).switchIfEmpty(Mono.just("idCustomer invalid!."));
	}

	@Override
	public Mono<Product> findByProductId(String id) {
		return productRepository.findById(id);
	}

	@Override
	public Flux<Product> findAllProduct() {
		return productRepository.findAll();
	}

	@Override
	public Mono<Product> updateProduct(Product e) {
		return productRepository.save(e);
	}

	@Override
	public Mono<Void> deleteProduct(String id) {
		return productRepository.deleteById(id);
	}

	@Override
	public Flux<Product> findByCustomerId(int id) {
		return productRepository.findByidCustomer(id);
	}

}
