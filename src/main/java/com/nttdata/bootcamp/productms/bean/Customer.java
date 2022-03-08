package com.nttdata.bootcamp.productms.bean;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Customer {
	int id;
	String customerType;
	String name;
	String lastName;
	String documentType;
	String documentNumber;
	String businessName;
}
