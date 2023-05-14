package github.denisspec989.retailexpertdemoservice.service.impl;

import github.denisspec989.retailexpertdemoservice.entity.Customer;
import github.denisspec989.retailexpertdemoservice.entity.Price;
import github.denisspec989.retailexpertdemoservice.entity.Product;
import github.denisspec989.retailexpertdemoservice.model.price.PriceDetailDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceParsingDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceShortDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceUpdateDto;
import github.denisspec989.retailexpertdemoservice.repository.PriceRepository;
import github.denisspec989.retailexpertdemoservice.service.CustomerService;
import github.denisspec989.retailexpertdemoservice.service.PriceService;
import github.denisspec989.retailexpertdemoservice.service.ProductService;
import github.denisspec989.retailexpertdemoservice.service.SerializablePriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;
    private final ProductService productService;
    private final CustomerService customerService;
    private final SerializablePriceService serializablePriceService;
    @Override
    @Transactional
    public Page<PriceShortDto> getPricesPaginated(Pageable pageable) {
        return priceRepository.findAll(pageable).map(serializablePriceService::fromPriceToPriceShortDto);
    }

    @Override
    @Transactional
    public PriceDetailDto getPriceDetail(UUID id) {
        return new PriceDetailDto(priceRepository.findById(id).orElseThrow());
    }

    @Override
    @Transactional
    public PriceDetailDto createNewPrice(PriceDetailDto priceDetailDto) {
        return new PriceDetailDto(priceRepository.save(serializablePriceService.fromPriceDetailDtoToPriceEntity(priceDetailDto)));
    }

    @Override
    @Transactional
    public PriceDetailDto updatePrice(PriceUpdateDto priceUpdateDto) {
        Price price = priceRepository.findById(priceUpdateDto.getId()).orElseThrow();
        price.setRegularPrice(priceUpdateDto.getRegularPrice());
        return new PriceDetailDto(priceRepository.save(price));
    }

    @Override
    @Transactional
    public void deletePrice(UUID id) {
        priceRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveAllParsedPrices(List<PriceParsingDto> priceParsingDtos) {
        priceRepository.saveAll(priceParsingDtos.stream().map(new Function<PriceParsingDto, Price>() {
            @Override
            public Price apply(PriceParsingDto priceParsingDto) {
                Optional<Customer> customer = customerService.getByGroceryChainName(priceParsingDto.getChainName());
                Optional<Product> product = productService.getProductByCode(priceParsingDto.getMaterialCode());
                if(product.isEmpty()||customer.isEmpty()){
                    return null;
                }
                Optional<Price> priceOptional = priceRepository.findByCustomerAndProductAndRegularPrice(customer.get(),product.get(),priceParsingDto.getRegularPrice());
                if(priceOptional.isEmpty()){
                    Price price = new Price();
                    price.setCustomer(customer.get());
                    price.setProduct(product.get());
                    price.setRegularPrice(priceParsingDto.getRegularPrice());
                    return price;
                }else {
                    return priceOptional.get();
                }
            }
        }).filter(entity->entity!=null).collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public Optional<Price> getPriceByCustomerAndProduct(Customer customer, Product product) {
        return priceRepository.findByCustomerAndProduct(customer,product);
    }

}
