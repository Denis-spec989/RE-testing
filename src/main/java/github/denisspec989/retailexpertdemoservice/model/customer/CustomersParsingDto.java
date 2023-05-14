package github.denisspec989.retailexpertdemoservice.model.customer;

import github.denisspec989.retailexpertdemoservice.model.common.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomersParsingDto {
    private AddressDto address;
    private String groceryChainName;
}
