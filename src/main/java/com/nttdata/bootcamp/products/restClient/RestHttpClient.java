package com.nttdata.bootcamp.products.restClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.bootcamp.products.bean.Customer;

@Component
public class RestHttpClient {
	private static final HttpClient client = HttpClient.newBuilder().version(Version.HTTP_2).build();
	private static final String serviceURL = "http://localhost:8080/customer";
	
	public Customer getCustomerById(int id) throws IOException, InterruptedException
	{
		HttpRequest req = HttpRequest.newBuilder(URI.create(serviceURL+"/"+id)).GET().build();
		HttpResponse<String> response = client.send(req, BodyHandlers.ofString());
		System.out.println("Hey look, an http response " + response.body());
		ObjectMapper objectMapper = new ObjectMapper();
		Customer customerRsp = objectMapper.readValue(response.body(), Customer.class);
		return customerRsp;
	}
}
