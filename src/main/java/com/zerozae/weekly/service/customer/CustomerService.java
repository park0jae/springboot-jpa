package com.zerozae.weekly.service.customer;

import com.zerozae.weekly.domain.customer.Customer;
import com.zerozae.weekly.dto.customer.CustomerCreateRequest;
import com.zerozae.weekly.dto.customer.CustomerResponse;
import com.zerozae.weekly.dto.customer.CustomerUpdateRequest;
import com.zerozae.weekly.repository.CustomerRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerResponse createCustomer(CustomerCreateRequest request) {
        Customer customer = customerRepository.save(request.toEntity());
        return CustomerResponse.toDto(customer);
    }

    @Transactional(readOnly = true)
    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
            .stream()
            .map(CustomerResponse::toDto)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CustomerResponse findCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("회원이 존재하지 않습니다."));

        return CustomerResponse.toDto(customer);
    }

    public CustomerResponse updateCustomer(Long id, CustomerUpdateRequest customerUpdateRequest) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("회원이 존재하지 않습니다."));

        customer.updateName(customerUpdateRequest.getName());
        return CustomerResponse.toDto(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("회원이 존재하지 않습니다."));

        customerRepository.deleteById(id);
    }
}
