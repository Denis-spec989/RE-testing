package github.denisspec989.retailexpertdemoservice.service;


import github.denisspec989.retailexpertdemoservice.entity.Price;
import github.denisspec989.retailexpertdemoservice.model.price.PriceDetailDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceShortDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PriceService {
    Page<PriceShortDto> getPricesPaginated(Pageable pageable);
    PriceDetailDto getPriceDetail(UUID id);
    PriceDetailDto createNewPrice(PriceDetailDto priceDetailDto);
    PriceDetailDto updatePrice(PriceUpdateDto priceUpdateDto);
    void deletePrice(UUID id);

}
