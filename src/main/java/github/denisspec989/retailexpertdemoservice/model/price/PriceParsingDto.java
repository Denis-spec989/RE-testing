package github.denisspec989.retailexpertdemoservice.model.price;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceParsingDto {
    private String chainName;
    private Long materialCode;
    private Double regularPrice;

}
