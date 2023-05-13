package github.denisspec989.retailexpertdemoservice.model.price;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceShortDto {
    private UUID id;
    private String groceryChainName;
    private String productName;
    private Double regularPrice;
}
