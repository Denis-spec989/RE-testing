package github.denisspec989.retailexpertdemoservice.model.common;

import github.denisspec989.retailexpertdemoservice.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private String value;
    private String code;
    public AddressDto(Address address){
        this.value=address.getValue();
        this.code=address.getCode();
    }
}
