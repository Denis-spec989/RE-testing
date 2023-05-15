package github.denisspec989.retailexpertdemoservice.model.customer;

import github.denisspec989.retailexpertdemoservice.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWithoutIdDto {
    private String groceryChainName;
    public CustomerWithoutIdDto(Customer customer){
        this.groceryChainName=customer.getGroceryChainName();
    }
}
