package com.zerozae.weekly.controller.customer;

import static org.springframework.http.HttpStatus.*;

import com.zerozae.weekly.dto.customer.CustomerCreateRequest;
import com.zerozae.weekly.dto.customer.CustomerResponse;
import com.zerozae.weekly.dto.customer.CustomerUpdateRequest;
import com.zerozae.weekly.service.customer.CustomerService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(CustomerCreateRequest request) {
        CustomerResponse customer = customerService.createCustomer(request);
        return ResponseEntity.created(URI.create("/api/v1/customers/" + customer.getId()))
            .body(customer);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllCustomers() {
        return ResponseEntity.status(OK).body(customerService.findAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable Long id) {
        return ResponseEntity.status(OK).body(customerService.findCustomerById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @RequestBody CustomerUpdateRequest request) {
        return ResponseEntity.status(OK).body(customerService.updateCustomer(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(NO_CONTENT).body("완료 되었습니다.");
    }
}
