package github.denisspec989.retailexpertdemoservice.model.price;

import github.denisspec989.retailexpertdemoservice.entity.Price;
import github.denisspec989.retailexpertdemoservice.model.customer.CustomerWithIdDto;
import github.denisspec989.retailexpertdemoservice.model.product.ProductWithoutCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceDetailDto {
    @NotNull
    @Valid
    private ProductWithoutCategoryDto product;
    @NotNull
    @Valid
    private CustomerWithIdDto customer;
    @NotNull
    private Double regularPrice;

    public PriceDetailDto(Price price){
        this.regularPrice=price.getRegularPrice();
        this.product=new ProductWithoutCategoryDto(price.getProduct());
        this.customer=new CustomerWithIdDto(price.getCustomer());
    }
}
