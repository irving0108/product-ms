package com.nttdata.bootcamp.productms.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase ProductType.
 * @version 1.0, 18/03/2022
 * @author Irving Chero
 */
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
