package github.denisspec989.retailexpertdemoservice.model.product;

import github.denisspec989.retailexpertdemoservice.entity.ProductCategory;
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
    public ProductSalesMonthlyDto(String groceryChainName, ProductCategory productCategory,Month month,Long unitsSoldByRegularPrice,Long unitsSoldByPromoPrice,Double promoPercent){
        this.customer=new ShipmentCustomerDto(groceryChainName);
        this.category=new ProductCategoryDto(productCategory);
        this.month=month;
        this.unitsSoldByRegularPrice=unitsSoldByRegularPrice;
        this.unitsSoldByPromoPrice=unitsSoldByPromoPrice;
        this.promoPercent=promoPercent;
    }
}
