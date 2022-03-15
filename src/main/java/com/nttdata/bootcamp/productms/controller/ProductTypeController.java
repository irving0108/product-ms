package com.nttdata.bootcamp.productms.controller;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.nttdata.bootcamp.productms.model.ProductType;
import com.nttdata.bootcamp.productms.service.ProductTypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product/type")
public class ProductTypeController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductTypeController.class);
	
	@Autowired
	ProductTypeService productService;
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public void createProductType(@RequestBody ProductType productType) {
		logger.info("ProductTypeController - createProductType - productType: {}", productType);
		productService.createProductType(productType);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<ProductType> updateProductType(@PathVariable("id") Integer id, @RequestBody ProductType productType) {
		logger.info("ProductTypeController - updateProductType - productType: {}", productType);
		return productService.updateProductType(productType);
	}
	
	@GetMapping(value = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@ResponseBody
	public Flux<ProductType> findAll(){
		logger.info("ProductTypeController - findAll");
		return productService.findAllProductType();
	}
	
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<Mono<ProductType>> findById(@PathVariable("id") Integer id){
		logger.info("ProductTypeController - findById - id: {}", id);
		Mono<ProductType> productmono = productService.findByProductTypeId(id);
		return new ResponseEntity<Mono<ProductType>>(productmono, productmono != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<ProductType> delete(@PathVariable("id") Integer id) {
		logger.info("ProductTypeController - delete - id: {}", id);
		final Mono<ProductType> dbproduct = productService.findByProductTypeId(id);
		if (Objects.isNull(dbproduct)) {
		   return Mono.empty();
		}
		return productService.findByProductTypeId(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(productToBeDeleted -> productService
		    .deleteProductType(id).then(Mono.just(productToBeDeleted)));
	}
}
