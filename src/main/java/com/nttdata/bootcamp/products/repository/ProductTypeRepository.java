package com.nttdata.bootcamp.products.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bootcamp.products.model.ProductType;

@Repository
public interface ProductTypeRepository extends ReactiveMongoRepository<ProductType, Integer>{

}
