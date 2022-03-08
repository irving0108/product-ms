package com.nttdata.bootcamp.productms.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class ProductType {
	int id;
	String productType;
	String productSubType;
	String description;
	double maintanceCommission;
	int maxMovementsLimit;
	
}
