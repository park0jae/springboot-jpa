package com.zerozae.weekly.dto.customer;

import com.zerozae.weekly.domain.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerCreateRequest {

    private String email;
    private String name;
    private String phoneNumber;

    public Customer toEntity() {
        return new Customer(
            email,
            name,
            phoneNumber
        );
    }
}
