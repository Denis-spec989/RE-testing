package github.denisspec989.retailexpertdemoservice.model.customer;

import github.denisspec989.retailexpertdemoservice.entity.Customer;
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
public class CustomerWithIdDto {
    @NotNull
    private UUID id;
    private String groceryChainName;
    public CustomerWithIdDto(Customer customer){
        this.id=customer.getId();
        this.groceryChainName=customer.getGroceryChainName();
    }
}
