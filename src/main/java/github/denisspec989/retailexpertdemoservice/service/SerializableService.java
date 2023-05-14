package github.denisspec989.retailexpertdemoservice.service;

import github.denisspec989.retailexpertdemoservice.entity.Price;
import github.denisspec989.retailexpertdemoservice.entity.Shipment;
import github.denisspec989.retailexpertdemoservice.model.price.PriceDetailDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceShortDto;
import github.denisspec989.retailexpertdemoservice.model.shipment.ShipmentDayDto;

public interface SerializableService {
    PriceShortDto fromPriceToPriceShortDto(Price price);
    Price fromPriceDetailDtoToPriceEntity(PriceDetailDto priceDetailDto);
    ShipmentDayDto fromShipmentToShipmentDto(Shipment shipment);
}
