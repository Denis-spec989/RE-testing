package github.denisspec989.retailexpertdemoservice.model.price;

import github.denisspec989.retailexpertdemoservice.entity.Customer;
import github.denisspec989.retailexpertdemoservice.entity.Price;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceCustomerDto {
    @NotNull
    private UUID id;
    private String groceryChainName;
    public  PriceCustomerDto(Customer customer){
        this.id=customer.getId();
        this.groceryChainName=customer.getGroceryChainName();
    }
}
