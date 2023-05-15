package github.denisspec989.retailexpertdemoservice.model.product;

import github.denisspec989.retailexpertdemoservice.entity.Product;
import github.denisspec989.retailexpertdemoservice.model.common.ProductCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithCategoryDto {
    private Long code;
    private String name;
    private ProductCategoryDto category;
    public ProductWithCategoryDto(Product product){
        this.code=product.getCode();
        this.name=product.getName();
        this.category=new ProductCategoryDto(product.getCategory());
    }
}
