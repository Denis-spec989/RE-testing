package github.denisspec989.retailexpertdemoservice.service;

import github.denisspec989.retailexpertdemoservice.entity.Price;
import github.denisspec989.retailexpertdemoservice.entity.Shipment;
import github.denisspec989.retailexpertdemoservice.model.price.PriceDetailDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceShortDto;
import github.denisspec989.retailexpertdemoservice.model.shipment.ShipmentDto;

public interface SerializableService {
    PriceShortDto fromPriceToPriceShortDto(Price price);
    Price fromPriceDetailDtoToPriceEntity(PriceDetailDto priceDetailDto);
    ShipmentDto fromShipmentToShipmentDto(Shipment shipment);
}
