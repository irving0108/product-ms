package com.nttdata.bootcamp.productms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.productms.model.ProductType;
import com.nttdata.bootcamp.productms.repository.ProductTypeRepository;
import com.nttdata.bootcamp.productms.service.ProductTypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementacicon del Service de la clase productType.
 * @version 1.0, 18/03/2022
 * @author Irving Chero
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService{
    
    @Autowired
    ProductTypeRepository productRepository;

    @Override
    public void createProductType(ProductType e) {
        productRepository.save(e).subscribe();
    }

    @Override
    public Mono<ProductType> findByProductTypeId(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Flux<ProductType> findAllProductType() {
        return productRepository.findAll();
    }

    @Override
    public Mono<ProductType> updateProductType(ProductType e) {
        return productRepository.save(e);
    }

    @Override
    public Mono<Void> deleteProductType(Integer id) {
        return productRepository.deleteById(id);
    }

}
