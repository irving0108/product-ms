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

import com.nttdata.bootcamp.productms.model.Product;
import com.nttdata.bootcamp.productms.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller que expone los servicios del product.
 * @version 1.0, 18/03/2022
 * @author Irving Chero
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    
    @Autowired
    ProductService operationService;
    
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> createOperation(@RequestBody Product operation) {
        logger.info("ProductController - createOperation - operation: {}", operation);
        return operationService.createProduct(operation);
    }
    
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Product> updateOperation(@PathVariable("id") Integer id, 
            @RequestBody Product operation) {
        logger.info("ProductController - updateOperation - operation: {}", operation);
        return operationService.updateProduct(operation);
    }
    
    @GetMapping(value = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<Product> findAll(){
        logger.info("ProductController - findAll");
        return operationService.findAllProduct();
    }
    
    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Mono<Product>> findById(@PathVariable("id") String id){
        logger.info("ProductController - findById - id: {}", id);
        Mono<Product> productmono = operationService.findByProductId(id);
        return new ResponseEntity<Mono<Product>>(productmono, productmono != null ? 
                HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    
    @GetMapping(value = "/customer/{id}")
    @ResponseBody
    public ResponseEntity<Flux<Product>> findByCustomerId(@PathVariable("id") int id){
        logger.info("ProductController - findByCustomerId - id: {}", id);
        Flux<Product> productmono = operationService.findByCustomerId(id);
        return new ResponseEntity<Flux<Product>>(productmono, productmono != null ? 
                HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Product> delete(@PathVariable("id") String id) {
        logger.info("ProductController - delete - id: {}", id);
        final Mono<Product> dbproduct = operationService.findByProductId(id);
        if (Objects.isNull(dbproduct)) {
           return Mono.empty();
        }
        return operationService.findByProductId(id).switchIfEmpty(Mono.empty())
                .filter(Objects::nonNull).flatMap(operationToBeDeleted -> operationService
            .deleteProduct(id).then(Mono.just(operationToBeDeleted)));
    }
}
