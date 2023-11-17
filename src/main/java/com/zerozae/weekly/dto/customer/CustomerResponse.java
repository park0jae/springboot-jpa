package com.zerozae.weekly.dto.customer;

import com.zerozae.weekly.domain.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerResponse {

    private Long id;
    private String email;
    private String name;
    private String phoneNumber;

    public static CustomerResponse toDto(Customer customer) {
        return new CustomerResponse(
            customer.getId(),
            customer.getEmail(),
            customer.getName(),
            customer.getPhoneNumber()
        );
    }

}
