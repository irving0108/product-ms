package com.nttdata.bootcamp.products.bean;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Customer {
	public int id;
	public String customerType;
	public String name;
	public String lastName;
	public String documentType;
	public String documentNumber;
	public String businessName;
}
