package com.nttdata.bootcamp.productms.restClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcamp.productms.bean.Customer;

import reactor.core.publisher.Mono;

@Component
public class RestHttpClient {
	@Value("${customer.types}")
	private static String serviceURL;
	
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
