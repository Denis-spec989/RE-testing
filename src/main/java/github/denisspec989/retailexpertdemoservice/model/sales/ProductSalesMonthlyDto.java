package github.denisspec989.retailexpertdemoservice.model.sales;

import github.denisspec989.retailexpertdemoservice.model.common.ProductCategoryDto;
import github.denisspec989.retailexpertdemoservice.model.shipment.ShipmentCustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSalesMonthlyDto {
    private ShipmentCustomerDto customer;
    private ProductCategoryDto category;
    private Month month;
    private Long unitsSoldByRegularPrice;
    private Long unitsSoldByPromoPrice;
    private Double promoPercent;
}
