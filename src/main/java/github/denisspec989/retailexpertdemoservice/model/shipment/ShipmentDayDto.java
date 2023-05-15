package github.denisspec989.retailexpertdemoservice.model.shipment;

import github.denisspec989.retailexpertdemoservice.entity.PromotionSign;
import github.denisspec989.retailexpertdemoservice.model.common.AddressDto;
import github.denisspec989.retailexpertdemoservice.model.customer.CustomerWithoutIdDto;
import github.denisspec989.retailexpertdemoservice.model.product.ProductWithCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDayDto {
    private String date;
    private CustomerWithoutIdDto customer;
    private ProductWithCategoryDto product;
    private AddressDto address;
    private PromotionSign promotionSign;
    private Long units;
    private Double salesValue;
}
