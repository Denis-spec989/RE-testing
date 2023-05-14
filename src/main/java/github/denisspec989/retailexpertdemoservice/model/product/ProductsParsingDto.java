package github.denisspec989.retailexpertdemoservice.model.product;

import github.denisspec989.retailexpertdemoservice.model.common.ProductCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsParsingDto {
    private Long number;
    private String name;
    private ProductCategoryDto category;
}
