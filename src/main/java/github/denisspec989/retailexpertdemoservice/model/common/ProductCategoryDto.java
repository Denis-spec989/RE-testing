package github.denisspec989.retailexpertdemoservice.model.common;

import github.denisspec989.retailexpertdemoservice.entity.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDto {
    private Long code;
    private String name;
    public ProductCategoryDto(ProductCategory productCategory){
        this.code=productCategory.getCode();
        this.name=productCategory.getName();
    }
}
