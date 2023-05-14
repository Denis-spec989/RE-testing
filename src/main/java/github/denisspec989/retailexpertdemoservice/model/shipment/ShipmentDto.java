package github.denisspec989.retailexpertdemoservice.model.shipment;

import github.denisspec989.retailexpertdemoservice.entity.PromotionSign;
import github.denisspec989.retailexpertdemoservice.model.common.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDto {
    private String date;
    private ShipmentCustomerDto customer;
    private ShipmentProductDto product;
    private AddressDto address;
    private PromotionSign promotionSign;
    private Double salesValue;
}
