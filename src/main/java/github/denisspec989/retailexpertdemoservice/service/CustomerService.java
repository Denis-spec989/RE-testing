package github.denisspec989.retailexpertdemoservice.service;

import github.denisspec989.retailexpertdemoservice.entity.Customer;

import java.util.UUID;

public interface CustomerService {
    Customer getCustomerById(UUID id);
}
