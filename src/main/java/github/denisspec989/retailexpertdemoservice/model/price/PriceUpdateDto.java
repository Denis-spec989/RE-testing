package github.denisspec989.retailexpertdemoservice.model.price;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceUpdateDto {
    @NotNull
    private UUID id;
    @NotNull
    private Double regularPrice;
}
