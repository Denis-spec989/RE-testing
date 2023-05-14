package github.denisspec989.retailexpertdemoservice.service.impl;

import github.denisspec989.retailexpertdemoservice.entity.Address;
import github.denisspec989.retailexpertdemoservice.model.common.AddressDto;
import github.denisspec989.retailexpertdemoservice.repository.AddressRepository;
import github.denisspec989.retailexpertdemoservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    @Override
    @Transactional
    public Address getAddressOrCreateNew(AddressDto addressDto) {
       return addressRepository.findByCodeAndValue(addressDto.getCode(), addressDto.getValue()).orElseGet(new Supplier<Address>() {
            @Override
            public Address get() {
                Address address = new Address();
                address.setCode(addressDto.getCode());
                address.setValue(addressDto.getValue());
                return addressRepository.save(address);
            }
        });
    }
}
