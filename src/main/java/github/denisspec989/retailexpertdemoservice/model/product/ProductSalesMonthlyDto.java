package github.denisspec989.retailexpertdemoservice.model.product;

import github.denisspec989.retailexpertdemoservice.entity.ProductCategory;
import github.denisspec989.retailexpertdemoservice.model.common.ProductCategoryDto;
import github.denisspec989.retailexpertdemoservice.model.customer.CustomerWithoutIdDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSalesMonthlyDto {
    private CustomerWithoutIdDto customer;
    private ProductCategoryDto category;
    private Integer year;
    private Integer month;
    private Long unitsSoldByRegularPrice;
    private Long unitsSoldByPromoPrice;
    private Double promoPercent;
    public ProductSalesMonthlyDto(String groceryChainName, ProductCategory productCategory,Integer year,Integer month,Long unitsSoldByRegularPrice,Long unitsSoldByPromoPrice,Double promoPercent){
        this.customer=new CustomerWithoutIdDto(groceryChainName);
        this.category=new ProductCategoryDto(productCategory);
        this.year=year;
        this.month=month;
        this.unitsSoldByRegularPrice=unitsSoldByRegularPrice;
        this.unitsSoldByPromoPrice=unitsSoldByPromoPrice;
        this.promoPercent=promoPercent;
    }
}
