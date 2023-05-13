package github.denisspec989.retailexpertdemoservice.model.price;

import github.denisspec989.retailexpertdemoservice.entity.Price;
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
    private PriceProductDto product;
    @NotNull
    @Valid
    private PriceCustomerDto customer;
    @NotNull
    private Double regularPrice;

    public PriceDetailDto(Price price){
        this.regularPrice=price.getRegularPrice();
        this.product=new PriceProductDto(price.getProduct());
        this.customer=new PriceCustomerDto(price.getCustomer());
    }
}
