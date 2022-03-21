package com.nttdata.bootcamp.productms.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bootcamp.productms.model.ProductType;


/**
 * Repository de class productType.
 * @version 1.0, 18/03/2022
 * @author Irving Chero
 */
@Repository
public interface ProductTypeRepository extends ReactiveMongoRepository<ProductType, Integer>{
}