package com.nttdata.bootcamp.products.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.products.model.Product;
import com.nttdata.bootcamp.products.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService operationService;
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public void createOperation(@RequestBody Product operation) {
		operationService.createProduct(operation);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Product> updateOperation(@PathVariable("id") Integer id, @RequestBody Product operation) {
		return operationService.updateProduct(operation);
	}
	
	@GetMapping(value = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@ResponseBody
	public Flux<Product> findAll(){
		return operationService.findAllProduct();
	}
	
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<Mono<Product>> findById(@PathVariable("id") String id){
		Mono<Product> productmono = operationService.findByProductId(id);
		return new ResponseEntity<Mono<Product>>(productmono, productmono != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/customer/{id}")
	@ResponseBody
	public ResponseEntity<Flux<Product>> findByCustomerId(@PathVariable("id") int id){
		Flux<Product> productmono = operationService.findByCustomerId(id);
		return new ResponseEntity<Flux<Product>>(productmono, productmono != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Product> delete(@PathVariable("id") String id) {
		final Mono<Product> dbproduct = operationService.findByProductId(id);
		if (Objects.isNull(dbproduct)) {
		   return Mono.empty();
		}
		return operationService.findByProductId(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(operationToBeDeleted -> operationService
		    .deleteProduct(id).then(Mono.just(operationToBeDeleted)));
	}
}
