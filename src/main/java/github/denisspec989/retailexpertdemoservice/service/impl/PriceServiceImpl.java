package github.denisspec989.retailexpertdemoservice.service.impl;

import github.denisspec989.retailexpertdemoservice.entity.Price;
import github.denisspec989.retailexpertdemoservice.model.price.PriceDetailDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceShortDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceUpdateDto;
import github.denisspec989.retailexpertdemoservice.repository.PriceRepository;
import github.denisspec989.retailexpertdemoservice.service.PriceService;
import github.denisspec989.retailexpertdemoservice.service.SerializablePriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;
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

}
