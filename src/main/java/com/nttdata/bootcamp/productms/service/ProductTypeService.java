package com.nttdata.bootcamp.productms.service;

import com.nttdata.bootcamp.productms.model.ProductType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service de la clase productType.
 * @version 1.0, 18/03/2022
 * @author Irving Chero
 */
public interface ProductTypeService {
    void createProductType(ProductType e);
    Mono<ProductType> findByProductTypeId(Integer id);
    Flux<ProductType> findAllProductType();
    Mono<ProductType> updateProductType(ProductType e);
    Mono<Void> deleteProductType(Integer id);
}
