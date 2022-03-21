package com.nttdata.bootcamp.productms.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * POJO Customer.
 * @version 1.0, 18/03/2022
 * @author Irving Chero
 */
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
