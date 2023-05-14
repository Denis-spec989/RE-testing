package github.denisspec989.retailexpertdemoservice.service;

import github.denisspec989.retailexpertdemoservice.entity.Address;
import github.denisspec989.retailexpertdemoservice.model.common.AddressDto;

public interface AddressService {
    Address getAddressOrCreateNew(AddressDto addressDto);
}
