package com.nttdata.bootcamp.productms.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bootcamp.productms.model.Product;

import reactor.core.publisher.Flux;

/**
 * Repository de class Product.
 * @version 1.0, 18/03/2022
 * @author Irving Chero
 */
@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String>{
    Flux<Product> findByidCustomer(int id);
}
