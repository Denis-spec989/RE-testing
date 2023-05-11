package github.denisspec989.retailexpertdemoservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceDTO {
    private String groceryChainName;
    private ProductDTO product; //связь Цена для каждой сети разная Один продукт много цен
    private Double defaultPrice;
}
