package com.nttdata.bootcamp.products.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.products.bean.Customer;
import com.nttdata.bootcamp.products.model.Product;
import com.nttdata.bootcamp.products.repository.ProductRepository;
import com.nttdata.bootcamp.products.restClient.RestHttpClient;
import com.nttdata.bootcamp.products.service.ProductService;
import com.nttdata.bootcamp.products.service.ProductTypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository operationRepository;
	
	@Autowired
	RestHttpClient restClient;
	
	@Autowired
	ProductTypeService productService;
	
	@Override
	public void createProduct(Product e) {
		try {
			Customer tmpCustomer = restClient.getCustomerById(e.idCustomer);
			if(tmpCustomer.id > 0) {
				operationRepository.save(e).subscribe();
			} else {
				throw new Exception();
			}
		}catch(Exception ex){
		}
	}

	@Override
	public Mono<Product> findByProductId(String id) {
		return operationRepository.findById(id);
	}

	@Override
	public Flux<Product> findAllProduct() {
		return operationRepository.findAll();
	}

	@Override
	public Mono<Product> updateProduct(Product e) {
		return operationRepository.save(e);
	}

	@Override
	public Mono<Void> deleteProduct(String id) {
		return operationRepository.deleteById(id);
	}

	@Override
	public Flux<Product> findByCustomerId(int id) {
		return operationRepository.findByidCustomer(id);
	}

}
