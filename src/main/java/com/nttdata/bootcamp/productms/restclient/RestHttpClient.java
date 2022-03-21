package com.nttdata.bootcamp.productms.restclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcamp.productms.bean.Customer;

import reactor.core.publisher.Mono;

/**
 * httpClient para consumir un servicio rest.
 * @version 1.0, 18/03/2022
 * @author Irving Chero
 */
@Component
public class RestHttpClient {
    
    @Value("${customer.ms.uri:uri}")
    private String serviceURL;
    
    public Mono<Customer> getCustomerById(int id){
        Mono<Customer> monoCustomer = WebClient 
            .create(serviceURL + "/" + id)
            .get()
            .retrieve()
            .bodyToMono(Customer.class);
        return monoCustomer;
    }
}
