package com.nttdata.bootcamp.productms.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bootcamp.productms.model.ProductType;

@Repository
public interface ProductTypeRepository extends ReactiveMongoRepository<ProductType, Integer>{

}
