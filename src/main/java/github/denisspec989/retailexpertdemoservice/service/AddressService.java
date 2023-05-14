package github.denisspec989.retailexpertdemoservice.service;

import github.denisspec989.retailexpertdemoservice.entity.Address;
import github.denisspec989.retailexpertdemoservice.model.common.AddressDto;

import java.util.Optional;

public interface AddressService {
    Address getAddressOrCreateNew(AddressDto addressDto);
}
