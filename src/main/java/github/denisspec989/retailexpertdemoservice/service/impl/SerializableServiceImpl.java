package github.denisspec989.retailexpertdemoservice.service.impl;

import github.denisspec989.retailexpertdemoservice.entity.Price;
import github.denisspec989.retailexpertdemoservice.entity.Product;
import github.denisspec989.retailexpertdemoservice.model.price.PriceDetailDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceShortDto;
import github.denisspec989.retailexpertdemoservice.service.CustomerService;
import github.denisspec989.retailexpertdemoservice.service.ProductService;
import github.denisspec989.retailexpertdemoservice.service.SerializableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SerializableServiceImpl implements SerializableService {
    private final CustomerService customerService;
    private final ProductService productService;
    @Override
    public PriceShortDto fromPriceToPriceShortDto(Price price) {
        PriceShortDto priceShortDto = PriceShortDto.builder()
                .id(price.getId())
                .groceryChainName(price.getCustomer().getGroceryChainName())
                .regularPrice(price.getRegularPrice())
                .productName(price.getProduct().getName())
                .build();
        return priceShortDto;
    }

    @Override
    public Price fromPriceDetailDtoToPriceEntity(PriceDetailDto priceDetailDto) {
        Price price = new Price();
        price.setRegularPrice(priceDetailDto.getRegularPrice());
        price.setCustomer(customerService.getCustomerById(priceDetailDto.getCustomer().getId()));
        price.setProduct(productService.getProductById(priceDetailDto.getProduct().getId()));
        return price ;
    }
}
