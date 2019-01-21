package com.kot.springjdbc;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Customer {
    private long id;
    private String firstName, lastName;
}

