package github.denisspec989.retailexpertdemoservice.service.impl;

import github.denisspec989.retailexpertdemoservice.entity.Customer;
import github.denisspec989.retailexpertdemoservice.model.customer.CustomersParsingDto;
import github.denisspec989.retailexpertdemoservice.repository.CustomerRepository;
import github.denisspec989.retailexpertdemoservice.service.CustomerService;
import github.denisspec989.retailexpertdemoservice.service.SerializableCustomerService;
import github.denisspec989.retailexpertdemoservice.service.SerializablePriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    @Transactional
    public Customer getCustomerById(UUID id) {
        return customerRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
       customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> getByGroceryChainName(String groceryChainName) {
        return customerRepository.findByGroceryChainName(groceryChainName);
    }

}
