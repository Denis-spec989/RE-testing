package github.denisspec989.retailexpertdemoservice.model.shipment;

import github.denisspec989.retailexpertdemoservice.entity.Customer;
import github.denisspec989.retailexpertdemoservice.entity.Shipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentCustomerDto {
    private String groceryChainName;
    public ShipmentCustomerDto(Customer customer){
        this.groceryChainName=customer.getGroceryChainName();
    }
}
