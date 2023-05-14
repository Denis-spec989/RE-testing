package github.denisspec989.retailexpertdemoservice.service;

import github.denisspec989.retailexpertdemoservice.entity.Customer;
import github.denisspec989.retailexpertdemoservice.model.customer.CustomersParsingDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    Customer getCustomerById(UUID id);
    void saveCustomer(Customer customer);
    Optional<Customer> getByGroceryChainName(String groceryChainName);
}
