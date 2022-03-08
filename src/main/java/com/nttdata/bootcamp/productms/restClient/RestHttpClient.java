package com.nttdata.bootcamp.productms.restClient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcamp.productms.bean.Customer;

import reactor.core.publisher.Mono;

@Component
public class RestHttpClient {
	private static final String serviceURL = "http://localhost:8080/customer";
	
	public Mono<Customer> getCustomerById(int id)
	{
		Mono<Customer> monoCustomer = WebClient
			.create(serviceURL+"/"+id)
			.get()
			.retrieve()
			.bodyToMono(Customer.class);
		return monoCustomer;
	}
}
