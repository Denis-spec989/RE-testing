package github.denisspec989.retailexpertdemoservice.model.price;

import github.denisspec989.retailexpertdemoservice.entity.Product;
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
public class PriceProductDto {
    @NotNull
    private UUID id;
    private String name;
    public PriceProductDto (Product product){
        this.id=product.getId();
        this.name=product.getName();
    }
}
