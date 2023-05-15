package github.denisspec989.retailexpertdemoservice.service.impl;

import github.denisspec989.retailexpertdemoservice.entity.Address;
import github.denisspec989.retailexpertdemoservice.entity.Customer;
import github.denisspec989.retailexpertdemoservice.model.customer.CustomersParsingDto;
import github.denisspec989.retailexpertdemoservice.repository.CustomerRepository;
import github.denisspec989.retailexpertdemoservice.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SerializableCustomerServiceImpl implements SerializableCustomerService {
    private final AddressService addressService;
    private final CustomerRepository customerRepository;

    @Override
    public Customer fromCustomerParsingDtoToCustomer(CustomersParsingDto customer) {
        Optional<Customer> customerOptional = customerRepository.findByGroceryChainName(customer.getGroceryChainName());
        if(customerOptional.isEmpty()){
            Customer customerEntity = new Customer();
            customerEntity.setGroceryChainName(customer.getGroceryChainName());
            List<Address> customerAddresses= new ArrayList<>();
            customerAddresses.add(addressService.getAddressOrCreateNew(customer.getAddress()));
            customerEntity.setAddresses(customerAddresses);
            return customerEntity;
        }else {
            Customer customerEntity = customerOptional.get();
            List<Address> customerAddresses=customerEntity.getAddresses();
            Address address = addressService.getAddressOrCreateNew(customer.getAddress());
            Optional<Address> filteredAddress = customerAddresses.stream().
                    filter(addressFiltering -> addressFiltering.equals(address)).findFirst();
            if(filteredAddress.isPresent()){
                return customerEntity;
            }else {
                customerAddresses.add(address);
                customerEntity.setAddresses(customerAddresses);
                return customerEntity;
            }
        }
    }
}
