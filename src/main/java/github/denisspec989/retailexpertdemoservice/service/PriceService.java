package github.denisspec989.retailexpertdemoservice.service;


import github.denisspec989.retailexpertdemoservice.entity.Customer;
import github.denisspec989.retailexpertdemoservice.entity.Price;
import github.denisspec989.retailexpertdemoservice.entity.Product;
import github.denisspec989.retailexpertdemoservice.model.price.PriceDetailDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceParsingDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceShortDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PriceService {
    Page<PriceShortDto> getPricesPaginated(Pageable pageable);
    PriceDetailDto getPriceDetail(UUID id);
    PriceDetailDto createNewPrice(PriceDetailDto priceDetailDto);
    PriceDetailDto updatePrice(PriceUpdateDto priceUpdateDto);
    void deletePrice(UUID id);
    void saveAllParsedPrices(List<PriceParsingDto> priceParsingDtos);
    Optional<Price> getPriceByCustomerAndProduct(Customer customer, Product product);
}
