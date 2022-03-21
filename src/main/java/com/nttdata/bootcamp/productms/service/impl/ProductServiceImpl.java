package com.nttdata.bootcamp.productms.service.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.productms.bean.Customer;
import com.nttdata.bootcamp.productms.model.Product;
import com.nttdata.bootcamp.productms.repository.ProductRepository;
import com.nttdata.bootcamp.productms.restclient.RestHttpClient;
import com.nttdata.bootcamp.productms.service.ProductService;
import com.nttdata.bootcamp.productms.service.ProductTypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementacicon del Service de la clase product.
 * @version 1.0, 18/03/2022
 * @author Irving Chero
 */
@Service
public class ProductServiceImpl implements ProductService{
    
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    RestHttpClient restClient;
    
    @Autowired
    ProductTypeService productTypeService;
    
    @Value("${customer.product.type.id:1,2,3,4,5,6}")
    private int[] idProductsType;
    
    @SuppressWarnings("unlikely-arg-type")
    @Override
    public Mono<String> createProduct(Product e) {
        logger.info("ProductServiceImpl - createProduct - INICIO");
        logger.info("cambio : {}", Arrays.toString(idProductsType));
        Mono<Customer> tmpCustomer = restClient.getCustomerById(e.getIdCustomer());
        if (!Arrays.asList(idProductsType).contains(e.getIdProduct())) {
            return Mono.just("idProduct invalid!.");
        }
        return tmpCustomer.map(response -> {
            productRepository.save(e).subscribe();
            logger.info("ProductServiceImpl - createProduct - FIN");
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
