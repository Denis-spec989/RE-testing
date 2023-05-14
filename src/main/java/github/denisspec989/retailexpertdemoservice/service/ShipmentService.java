package github.denisspec989.retailexpertdemoservice.service;

import github.denisspec989.retailexpertdemoservice.entity.PromotionSign;
import github.denisspec989.retailexpertdemoservice.model.shipment.ActualsParsingDto;
import github.denisspec989.retailexpertdemoservice.model.shipment.ShipmentDayDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShipmentService {
    Page<ShipmentDayDto> getShipmentsFilteredAndPaginated(Pageable pageable, List<String> groceryChainNames, List<Long> productCodes, String date, PromotionSign promotionSign);
    void saveAllParsedShipments(List<ActualsParsingDto> actuals);
}
