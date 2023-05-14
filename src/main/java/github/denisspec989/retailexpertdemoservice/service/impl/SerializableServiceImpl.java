package github.denisspec989.retailexpertdemoservice.service.impl;

import github.denisspec989.retailexpertdemoservice.entity.Price;
import github.denisspec989.retailexpertdemoservice.entity.Shipment;
import github.denisspec989.retailexpertdemoservice.model.common.AddressDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceDetailDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceShortDto;
import github.denisspec989.retailexpertdemoservice.model.shipment.ShipmentCustomerDto;
import github.denisspec989.retailexpertdemoservice.model.shipment.ShipmentDayDto;
import github.denisspec989.retailexpertdemoservice.model.shipment.ShipmentProductDto;
import github.denisspec989.retailexpertdemoservice.service.CustomerService;
import github.denisspec989.retailexpertdemoservice.service.ProductService;
import github.denisspec989.retailexpertdemoservice.service.SerializableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
@RequiredArgsConstructor
public class SerializableServiceImpl implements SerializableService {
    private final CustomerService customerService;
    private final ProductService productService;
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
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

    @Override
    public ShipmentDayDto fromShipmentToShipmentDto(Shipment shipment) {
        ShipmentDayDto shipmentDayDto = new ShipmentDayDto();
        shipmentDayDto.setSalesValue(shipment.getSaleValue());
        shipmentDayDto.setUnits(shipment.getUnits());
        shipmentDayDto.setDate(simpleDateFormat.format(shipment.getDate()));
        shipmentDayDto.setAddress(new AddressDto(shipment.getAddress()));
        shipmentDayDto.setPromotionSign(shipment.getPromotionSign());
        shipmentDayDto.setCustomer(new ShipmentCustomerDto(shipment.getCustomer()));
        shipmentDayDto.setProduct(new ShipmentProductDto(shipment.getProduct()));
        return shipmentDayDto;
    }
}
